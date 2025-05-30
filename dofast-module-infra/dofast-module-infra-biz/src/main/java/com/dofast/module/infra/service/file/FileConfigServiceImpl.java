package com.dofast.module.infra.service.file;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.IdUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.json.JsonUtils;
import com.dofast.framework.common.util.validation.ValidationUtils;
import com.dofast.framework.file.core.client.FileClient;
import com.dofast.framework.file.core.client.FileClientConfig;
import com.dofast.framework.file.core.client.FileClientFactory;
import com.dofast.framework.file.core.enums.FileStorageEnum;
import com.dofast.module.infra.controller.admin.file.vo.config.FileConfigCreateReqVO;
import com.dofast.module.infra.controller.admin.file.vo.config.FileConfigPageReqVO;
import com.dofast.module.infra.controller.admin.file.vo.config.FileConfigUpdateReqVO;
import com.dofast.module.infra.convert.file.FileConfigConvert;
import com.dofast.module.infra.dal.dataobject.file.FileConfigDO;
import com.dofast.module.infra.dal.mysql.file.FileConfigMapper;

import com.dofast.module.infra.mq.producer.file.FileConfigProducer;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;

import static com.dofast.module.infra.enums.ErrorCodeConstants.FILE_CONFIG_DELETE_FAIL_MASTER;
import static com.dofast.module.infra.enums.ErrorCodeConstants.FILE_CONFIG_NOT_EXISTS;

/**
 * 文件配置 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class FileConfigServiceImpl implements FileConfigService {


    @Resource
    private FileClientFactory fileClientFactory;
    /**
     * Master FileClient 对象，有且仅有一个，即 {@link FileConfigDO#getMaster()} 对应的
     */
    @Getter
    private FileClient masterFileClient;
    private static final Long CACHE_MASTER_ID = 0L;

    /**
     * {@link FileClient} 缓存，通过它异步刷新 fileClientFactory
     */
    @Getter
    private final LoadingCache<Long, FileClient> clientCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, FileClient>() {

                @Override
                public FileClient load(Long id) {
                    FileConfigDO config = Objects.equals(CACHE_MASTER_ID, id) ?
                            fileConfigMapper.selectByMaster() : fileConfigMapper.selectById(id);
                    if (config != null) {
                        fileClientFactory.createOrUpdateFileClient(id, config.getStorage(), config.getConfig());
                    }
                    return fileClientFactory.getFileClient(id);
                }

             });



    @Resource
    private FileConfigMapper fileConfigMapper;

    @Resource

    private FileConfigProducer fileConfigProducer;

    @Resource
    private Validator validator;

    @Override
    @PostConstruct
    public void initLocalCache() {
        // 第一步：查询数据
        List<FileConfigDO> configs = fileConfigMapper.selectList();
        log.info("[initLocalCache][缓存文件配置，数量为:{}]", configs.size());

        // 第二步：构建缓存：创建或更新文件 Client
        configs.forEach(config -> {
            fileClientFactory.createOrUpdateFileClient(config.getId(), config.getStorage(), config.getConfig());
            // 如果是 master，进行设置
            if (Boolean.TRUE.equals(config.getMaster())) {
                masterFileClient = fileClientFactory.getFileClient(config.getId());
            }
        });
    }



    @Override
    public Long createFileConfig(FileConfigCreateReqVO createReqVO) {

        FileConfigDO fileConfig = FileConfigConvert.INSTANCE.convert(createReqVO)
                .setConfig(parseClientConfig(createReqVO.getStorage(), createReqVO.getConfig()))
                .setMaster(false); // 默认非 master
        fileConfigMapper.insert(fileConfig);

        // 发送刷新配置的消息
        fileConfigProducer.sendFileConfigRefreshMessage();
        // 返回

        return fileConfig.getId();
    }

    @Override
    public void updateFileConfig(FileConfigUpdateReqVO updateReqVO) {
        // 校验存在
        FileConfigDO config = validateFileConfigExists(updateReqVO.getId());
        // 更新
        FileConfigDO updateObj = FileConfigConvert.INSTANCE.convert(updateReqVO)
                .setConfig(parseClientConfig(config.getStorage(), updateReqVO.getConfig()));
        fileConfigMapper.updateById(updateObj);

        // 发送刷新配置的消息
        fileConfigProducer.sendFileConfigRefreshMessage();

        // 清空缓存
        clearCache(config.getId(), null);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFileConfigMaster(Long id) {
        // 校验存在
        validateFileConfigExists(id);
        // 更新其它为非 master
        fileConfigMapper.updateBatch(new FileConfigDO().setMaster(false));
        // 更新
        fileConfigMapper.updateById(new FileConfigDO().setId(id).setMaster(true));

        // 发送刷新配置的消息
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                fileConfigProducer.sendFileConfigRefreshMessage();
            }

        });

        // 清空缓存
        clearCache(null, true);

    }

    private FileClientConfig parseClientConfig(Integer storage, Map<String, Object> config) {
        // 获取配置类
        Class<? extends FileClientConfig> configClass = FileStorageEnum.getByStorage(storage)
                .getConfigClass();
        FileClientConfig clientConfig = JsonUtils.parseObject2(JsonUtils.toJsonString(config), configClass);
        // 参数校验
        ValidationUtils.validate(validator, clientConfig);
        // 设置参数
        return clientConfig;
    }

    @Override
    public void deleteFileConfig(Long id) {
        // 校验存在
        FileConfigDO config = validateFileConfigExists(id);
        if (Boolean.TRUE.equals(config.getMaster())) {
             throw exception(FILE_CONFIG_DELETE_FAIL_MASTER);
        }
        // 删除
        fileConfigMapper.deleteById(id);

        // 发送刷新配置的消息
        fileConfigProducer.sendFileConfigRefreshMessage();

        // 清空缓存
        clearCache(id, null);
    }

    /**
     * 清空指定文件配置
     *
     * @param id 配置编号
     * @param master 是否主配置
     */
    private void clearCache(Long id, Boolean master) {
        if (id != null) {
            clientCache.invalidate(id);
        }
        if (Boolean.TRUE.equals(master)) {
            clientCache.invalidate(CACHE_MASTER_ID);
        }

    }

    private FileConfigDO validateFileConfigExists(Long id) {
        FileConfigDO config = fileConfigMapper.selectById(id);
        if (config == null) {
            throw exception(FILE_CONFIG_NOT_EXISTS);
        }
        return config;
    }

    @Override
    public FileConfigDO getFileConfig(Long id) {
        return fileConfigMapper.selectById(id);
    }

    @Override
    public PageResult<FileConfigDO> getFileConfigPage(FileConfigPageReqVO pageReqVO) {
        return fileConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public String testFileConfig(Long id) throws Exception {
        // 校验存在
        validateFileConfigExists(id);
        // 上传文件
        byte[] content = ResourceUtil.readBytes("file/erweima.jpg");
        return getFileClient(id).upload(content, IdUtil.fastSimpleUUID() + ".jpg", "image/jpeg");
    }

    @Override
    public FileClient getFileClient(Long id) {
        return clientCache.getUnchecked(id);
    }

    @Override
    public FileClient getMasterFileClient() {
        return clientCache.getUnchecked(CACHE_MASTER_ID);

    }

}
