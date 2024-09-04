package com.dofast.module.pay.service.channel;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.exception.util.ServiceExceptionUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.json.JsonUtils;
import com.dofast.framework.pay.core.client.PayClient;
import com.dofast.framework.pay.core.client.PayClientConfig;
import com.dofast.framework.pay.core.client.PayClientFactory;
import com.dofast.framework.pay.core.enums.channel.PayChannelEnum;
import com.dofast.framework.tenant.core.util.TenantUtils;
import com.dofast.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import com.dofast.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;
import com.dofast.module.pay.controller.admin.merchant.vo.channel.PayChannelExportReqVO;
import com.dofast.module.pay.controller.admin.merchant.vo.channel.PayChannelPageReqVO;
import com.dofast.module.pay.convert.channel.PayChannelConvert;
import com.dofast.module.pay.dal.dataobject.channel.PayChannelDO;
import com.dofast.module.pay.dal.mysql.channel.PayChannelMapper;
import com.dofast.module.pay.enums.ErrorCodeConstants;
import com.dofast.module.pay.framework.pay.core.WalletPayClient;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Validator;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;
import static com.dofast.module.pay.enums.ErrorCodeConstants.*;

/**
 * 支付渠道 Service 实现类
 *
 * @author aquan
 */
@Service("payChannelServiceImplA")
@Slf4j
@Validated
public class PayChannelServiceImpl implements PayChannelService {

    /**
     * {@link PayClient} 缓存，通过它异步清空 smsClientFactory
     */
    @Getter
    private final LoadingCache<Long, PayClient> clientCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, PayClient>() {

                @Override
                public PayClient load(Long id) {
                    // 查询，然后尝试清空
                    PayChannelDO channel = payChannelMapper.selectById(id);
                    if (channel != null) {
                        payClientFactory.createOrUpdatePayClient(channel.getId(), channel.getCode(), channel.getConfig());
                    }
                    return payClientFactory.getPayClient(id);
                }

            });

    @Resource
    private PayClientFactory payClientFactory;

    @Resource
    private PayChannelMapper payChannelMapper;

    @Resource
    private Validator validator;


    /**
     * 缓存菜单的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile LocalDateTime maxUpdateTime;


    @Resource
    private PayChannelMapper channelMapper;

    /**
     * 初始化，为了注册钱包
     */
    @PostConstruct
    public void init() {
        payClientFactory.registerPayClientClass(PayChannelEnum.WALLET, WalletPayClient.class);
    }

    @Override
    public Long createChannel(PayChannelCreateReqVO reqVO) {
        // 断言是否有重复的
        PayChannelDO dbChannel = getChannelByAppIdAndCode(reqVO.getAppId(), reqVO.getCode());
        if (dbChannel != null) {
            throw exception(CHANNEL_EXIST_SAME_CHANNEL_ERROR);
        }

        // 新增渠道
        PayChannelDO channel = PayChannelConvert.INSTANCE.convert(reqVO)
                .setConfig(parseConfig(reqVO.getCode(), reqVO.getConfig()));
        payChannelMapper.insert(channel);
        return channel.getId();
    }

    @Override
    public void updateChannel(PayChannelUpdateReqVO updateReqVO) {
        // 校验存在
        PayChannelDO dbChannel = validateChannelExists(updateReqVO.getId());

        // 更新
        PayChannelDO channel = PayChannelConvert.INSTANCE.convert(updateReqVO)
                .setConfig(parseConfig(dbChannel.getCode(), updateReqVO.getConfig()));
        payChannelMapper.updateById(channel);

        // 清空缓存
        clearCache(channel.getId());
    }

    /**
     * 解析并校验配置
     *
     * @param code      渠道编码
     * @param configStr 配置
     * @return 支付配置
     */
    private PayClientConfig parseConfig(String code, String configStr) {
        // 解析配置
        Class<? extends PayClientConfig> payClass = PayChannelEnum.getByCode(code).getConfigClass();
        if (ObjectUtil.isNull(payClass)) {
            throw exception(CHANNEL_NOT_FOUND);
        }
        PayClientConfig config = JsonUtils.parseObject2(configStr, payClass);
        Assert.notNull(config);

        // 验证参数
        config.validate(validator);
        return config;
    }

    @Override
    public void deleteChannel(Long id) {
        // 校验存在
        validateChannelExists(id);

        // 删除
        payChannelMapper.deleteById(id);

        // 清空缓存
        clearCache(id);
    }

    /**
     * 删除缓存
     *
     * @param id 渠道编号
     */
    private void clearCache(Long id) {
        clientCache.invalidate(id);
    }

    private PayChannelDO validateChannelExists(Long id) {
        PayChannelDO channel = payChannelMapper.selectById(id);
        if (channel == null) {
            throw exception(CHANNEL_NOT_FOUND);
        }
        return channel;
    }

    @Override
    public PayChannelDO getChannel(Long id) {
        return payChannelMapper.selectById(id);
    }

    @Override
    public List<PayChannelDO> getChannelListByAppIds(Collection<Long> appIds) {
        return payChannelMapper.selectListByAppIds(appIds);
    }

    @Override
    public PayChannelDO getChannelByAppIdAndCode(Long appId, String code) {
        return payChannelMapper.selectByAppIdAndCode(appId, code);
    }

    @Override
    public PayChannelDO validPayChannel(Long id) {
        PayChannelDO channel = payChannelMapper.selectById(id);
        validPayChannel(channel);
        return channel;
    }

    @Override
    public PayChannelDO validPayChannel(Long appId, String code) {
        PayChannelDO channel = payChannelMapper.selectByAppIdAndCode(appId, code);
        validPayChannel(channel);
        return channel;
    }

    private void validPayChannel(PayChannelDO channel) {
        if (channel == null) {
            throw exception(CHANNEL_NOT_FOUND);
        }
        if (CommonStatusEnum.DISABLE.getStatus().equals(channel.getStatus())) {
            throw exception(CHANNEL_IS_DISABLE);
        }
    }

    @Override
    public List<PayChannelDO> getEnableChannelList(Long appId) {
        return payChannelMapper.selectListByAppId(appId, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public PayClient getPayClient(Long id) {
        return clientCache.getUnchecked(id);
    }




    /**
     * 初始化 {@link #payClientFactory} 缓存
     */
    @Override
    @PostConstruct
    public void initLocalCache() {
        // 注意：忽略自动多租户，因为要全局初始化缓存
        TenantUtils.executeIgnore(() -> {
            // 第一步：查询数据
            List<PayChannelDO> channels = channelMapper.selectList();
            log.info("[initLocalCache][缓存支付渠道，数量为:{}]", channels.size());

            // 第二步：构建缓存：创建或更新支付 Client
            channels.forEach(payChannel -> payClientFactory.createOrUpdatePayClient(payChannel.getId(),
                    payChannel.getCode(), payChannel.getConfig()));
        });
    }
    @Override
    public List<PayChannelDO> getChannelList(Collection<Long> ids) {
        return channelMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PayChannelDO> getChannelPage(PayChannelPageReqVO pageReqVO) {
        return channelMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PayChannelDO> getChannelList(PayChannelExportReqVO exportReqVO) {
        return channelMapper.selectList(exportReqVO);
    }




    /**
     * 根据条件获取渠道数量
     *
     * @param merchantId 商户编号
     * @param appid      应用编号
     * @param code       渠道编码
     * @return 数量
     */
    @Override
    public Integer getChannelCountByConditions(Long merchantId, Long appid, String code) {
        return this.channelMapper.selectCount(merchantId, appid, code);
    }

    /**
     * 根据条件获取渠道
     *
     * @param merchantId 商户编号
     * @param appid      应用编号
     * @param code       渠道编码
     * @return 数量
     */
    @Override
    public PayChannelDO getChannelByConditions(Long merchantId, Long appid, String code) {
        return this.channelMapper.selectOne(merchantId, appid, code);
    }

    /**
     * 设置渠道配置以及参数校验
     *
     * @param channel   渠道
     * @param configStr 配置
     */
    private void settingConfigAndCheckParam(PayChannelDO channel, String configStr) {
        // 得到这个渠道是微信的还是支付宝的
        Class<? extends PayClientConfig> payClass = com.dofast.framework.pay.core.enums.PayChannelEnum.getByCode(channel.getCode()).getConfigClass();
        if (ObjectUtil.isNull(payClass)) {
            throw exception(CHANNEL_NOT_EXISTS);
        }
        // TODO @芋艿：不要使用 hutool 的 json 工具，用项目的
        PayClientConfig config = JSONUtil.toBean(configStr, payClass);

        // 验证参数
        config.validate(validator);
        channel.setConfig(config);
    }

}
