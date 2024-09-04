package com.dofast.module.pay.service.transfer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.json.JsonUtils;
import com.dofast.framework.pay.core.client.PayClient;
import com.dofast.framework.pay.core.client.dto.transfer.PayTransferRespDTO;
import com.dofast.framework.pay.core.enums.channel.PayChannelEnum;
import com.dofast.framework.pay.core.enums.transfer.PayTransferStatusRespEnum;
import com.dofast.framework.pay.core.enums.transfer.PayTransferTypeEnum;
import com.dofast.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import com.dofast.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import com.dofast.module.pay.controller.admin.transfer.vo.PayTransferPageReqVO;
import com.dofast.module.pay.convert.transfer.PayTransferConvert;
import com.dofast.module.pay.dal.dataobject.channel.PayChannelDO;
import com.dofast.module.pay.dal.dataobject.transfer.PayTransferDO;
import com.dofast.module.pay.dal.mysql.transfer.PayTransferMapper;
import com.dofast.module.pay.dal.redis.no.PayNoRedisDAO;
import com.dofast.module.pay.enums.transfer.PayTransferStatusEnum;
import com.dofast.module.pay.service.app.PayAppService;
import com.dofast.module.pay.service.channel.PayChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Validator;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pay.convert.transfer.PayTransferConvert.INSTANCE;
import static com.dofast.module.pay.enums.ErrorCodeConstants.*;
import static com.dofast.module.pay.enums.transfer.PayTransferStatusEnum.*;

// TODO @jason：等彻底实现完，单测写写；
/**
 * 转账 Service 实现类
 *
 * @author jason
 */
@Service
@Slf4j
public class PayTransferServiceImpl implements PayTransferService {

    private static final String TRANSFER_NO_PREFIX = "T";

    @Autowired
    private PayTransferMapper transferMapper;

    @Resource
    private PayAppService appService;
    @Resource
    private PayChannelService channelService;

    @Resource
    private PayNoRedisDAO noRedisDAO;

    @Resource
    private Validator validator;
    @Override
    public PayTransferDO createTransfer(PayTransferCreateReqVO reqVO, String userIp) {
        // 1. 校验参数
        reqVO.validate(validator);

        // 2. 创建转账单，发起转账
        PayTransferCreateReqDTO req = INSTANCE.convert(reqVO).setUserIp(userIp);
        Long transferId = createTransfer(req);

        // 3. 返回转账单
        return getTransfer(transferId);
    }

    @Override
    public Long createTransfer(PayTransferCreateReqDTO reqDTO) {
        // 校验 App
        appService.validPayApp(reqDTO.getAppId());
        // 创建转账单
        PayTransferDO transfer = PayTransferConvert.INSTANCE.convert(reqDTO)
                        .setStatus(WAITING.getStatus());
        transferMapper.insert(transfer);
        return transfer.getId();
    }

    @Override
    public PayTransferDO getTransfer(Long id) {
        return transferMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    // 注意，如果是方法内调用该方法，需要通过 getSelf().notifyTransfer(channel, notify) 调用，否则事务不生效
    public void notifyTransfer(PayChannelDO channel, PayTransferRespDTO notify) {
        // 转账成功的回调
        if (PayTransferStatusRespEnum.isSuccess(notify.getStatus())) {
            notifyTransferSuccess(channel, notify);
        }
        // 转账关闭的回调
        if (PayTransferStatusRespEnum.isClosed(notify.getStatus())) {
            notifyTransferClosed(channel, notify);
        }
        // WAITING 状态无需处理
        // TODO IN_PROGRESS 待处理
    }

    private void notifyTransferSuccess(PayChannelDO channel, PayTransferRespDTO notify) {
        // 1. 更新 PayTransferDO 转账成功
        Boolean transferred = updateTransferSuccess(channel, notify);
        if (transferred) {
            return;
        }
        // 2. TODO 插入转账通知记录
    }

    private Boolean updateTransferSuccess(PayChannelDO channel, PayTransferRespDTO notify) {
        // 1.校验
        PayTransferDO transfer = transferMapper.selectByNo(notify.getOutTransferNo());
        if (transfer == null) {
            throw exception(PAY_TRANSFER_NOT_FOUND);
        }
        if (isSuccess(transfer.getStatus())) { // 如果已成功，直接返回，不用重复更新
            return Boolean.TRUE;
        }
        if (!isPendingStatus(transfer.getStatus())) {
            throw exception(PAY_TRANSFER_STATUS_IS_NOT_PENDING);
        }
        // 2.更新
        int updateCounts = transferMapper.updateByIdAndStatus(transfer.getId(),
                CollUtil.newArrayList(WAITING.getStatus(), IN_PROGRESS.getStatus()),
                new PayTransferDO().setStatus(SUCCESS.getStatus()).setSuccessTime(notify.getSuccessTime())
                        .setChannelTransferNo(notify.getChannelTransferNo())
                        .setChannelId(channel.getId()).setChannelCode(channel.getCode())
                        .setChannelNotifyData(JsonUtils.toJsonString(notify)));
        if (updateCounts == 0) {
            throw exception(PAY_TRANSFER_STATUS_IS_NOT_PENDING);
        }
        log.info("[updateTransferSuccess][transfer({}) 更新为已转账]", transfer.getId());
        return Boolean.FALSE;
    }



    private void notifyTransferClosed(PayChannelDO channel, PayTransferRespDTO notify) {
        //  更新 PayTransferExtensionDO 转账关闭
        updateTransferClosed(channel, notify);
    }

    private void updateTransferClosed(PayChannelDO channel, PayTransferRespDTO notify) {
        // 1.校验
        PayTransferDO transfer = transferMapper.selectByNo(notify.getOutTransferNo());
        if (transfer == null) {
            throw exception(PAY_TRANSFER_NOT_FOUND);
        }
        if (isClosed(transfer.getStatus())) { // 如果已是关闭状态，直接返回，不用重复更新
            log.info("[updateTransferClosed][transfer({}) 已经是关闭状态，无需更新]", transfer.getId());
            return;
        }
        if (!isPendingStatus(transfer.getStatus())) {
            throw exception(PAY_TRANSFER_STATUS_IS_NOT_PENDING);
        }
        // 2.更新
        int updateCount = transferMapper.updateByIdAndStatus(transfer.getId(),
                CollUtil.newArrayList(WAITING.getStatus(), IN_PROGRESS.getStatus()),
                new PayTransferDO().setStatus(CLOSED.getStatus()).setChannelId(channel.getId())
                        .setChannelCode(channel.getCode()).setChannelTransferNo(notify.getChannelTransferNo())
                        .setChannelErrorCode(notify.getChannelErrorCode()).setChannelErrorMsg(notify.getChannelErrorMsg())
                        .setChannelNotifyData(JsonUtils.toJsonString(notify)));
        if (updateCount == 0) {
            throw exception(PAY_TRANSFER_STATUS_IS_NOT_PENDING);
        }
        log.info("[updateTransferClosed][transfer({}) 更新为关闭状态]", transfer.getId());
    }



    private void validateChannelCodeAndTypeMatch(String channelCode, Integer type) {
        PayTransferTypeEnum transferType = PayTransferTypeEnum.typeOf(type);
        PayChannelEnum payChannel = PayChannelEnum.getByCode(channelCode);
        switch (transferType) {
            case ALIPAY_BALANCE: {
                // TODO @jason：可以抽到 PayChannelEnum 里，isAlipay？ 类似这种哈
                if (!payChannel.getCode().startsWith("alipay")) {
                    throw exception(PAY_TRANSFER_TYPE_AND_CHANNEL_NOT_MATCH);
                }
                break;
            }
            case WX_BALANCE:
            case BANK_CARD:
            case WALLET_BALANCE: {
                throw new UnsupportedOperationException("待实现");
            }
        }
    }





    /**
     * 获得自身的代理对象，解决 AOP 生效问题
     *
     * @return 自己
     */
    private PayTransferServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }

    @Override
    public PageResult<PayTransferDO> getTransferPage(PayTransferPageReqVO pageReqVO) {
        return transferMapper.selectPage(pageReqVO);
    }
}
