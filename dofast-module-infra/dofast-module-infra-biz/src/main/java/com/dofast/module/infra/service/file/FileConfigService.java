package com.dofast.module.infra.service.file;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.file.core.client.FileClient;
import com.dofast.module.infra.controller.admin.file.vo.config.FileConfigCreateReqVO;
import com.dofast.module.infra.controller.admin.file.vo.config.FileConfigPageReqVO;
import com.dofast.module.infra.controller.admin.file.vo.config.FileConfigUpdateReqVO;
import com.dofast.module.infra.dal.dataobject.file.FileConfigDO;

import javax.validation.Valid;

/**
 * 文件配置 Service 接口
 *
 * @author 芋道源码
 */
public interface FileConfigService {

    /**

     * 初始化文件客户端
     */
    void initLocalCache();

    /**


     * 创建文件配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFileConfig(@Valid FileConfigCreateReqVO createReqVO);

    /**
     * 更新文件配置
     *
     * @param updateReqVO 更新信息
     */
    void updateFileConfig(@Valid FileConfigUpdateReqVO updateReqVO);

    /**
     * 更新文件配置为 Master
     *
     * @param id 编号
     */
    void updateFileConfigMaster(Long id);

    /**
     * 删除文件配置
     *
     * @param id 编号
     */
    void deleteFileConfig(Long id);

    /**
     * 获得文件配置
     *
     * @param id 编号
     * @return 文件配置
     */
    FileConfigDO getFileConfig(Long id);

    /**
     * 获得文件配置分页
     *
     * @param pageReqVO 分页查询
     * @return 文件配置分页
     */
    PageResult<FileConfigDO> getFileConfigPage(FileConfigPageReqVO pageReqVO);

    /**
     * 测试文件配置是否正确，通过上传文件
     *
     * @param id 编号
     * @return 文件 URL
     */
    String testFileConfig(Long id) throws Exception;

    /**
     * 获得指定编号的文件客户端
     *
     * @param id 配置编号
     * @return 文件客户端
     */
    FileClient getFileClient(Long id);

    /**
     * 获得 Master 文件客户端
     *
     * @return 文件客户端
     */
    FileClient getMasterFileClient();

}
