package com.dofast.module.pay.dal.mysql.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;




import com.dofast.framework.mybatis.core.query.QueryWrapperX;
import com.dofast.module.pay.controller.admin.order.vo.PayOrderExportReqVO;
import com.dofast.module.pay.controller.admin.order.vo.PayOrderPageReqVO;
import com.dofast.module.pay.dal.dataobject.order.PayOrderDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PayOrderMapper extends BaseMapperX<PayOrderDO> {

    default PageResult<PayOrderDO> selectPage(PayOrderPageReqVO reqVO) {

        return selectPage(reqVO, new QueryWrapperX<PayOrderDO>()
                .eqIfPresent("merchant_id", reqVO.getMerchantId())
                .eqIfPresent("app_id", reqVO.getAppId())
                .eqIfPresent("channel_id", reqVO.getChannelId())
                .eqIfPresent("channel_code", reqVO.getChannelCode())
                .likeIfPresent("merchant_order_id", reqVO.getMerchantOrderId())
                .eqIfPresent("notify_status", reqVO.getNotifyStatus())
                .eqIfPresent("status", reqVO.getStatus())
                .eqIfPresent("refund_status", reqVO.getRefundStatus())
                .likeIfPresent("channel_order_no", reqVO.getChannelOrderNo())
                .betweenIfPresent("create_time", reqVO.getCreateTime())
                .orderByDesc("id"));
    }


    default List<PayOrderDO> selectList(PayOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PayOrderDO>()
                .eqIfPresent(PayOrderDO::getAppId, reqVO.getAppId())
                .eqIfPresent(PayOrderDO::getChannelCode, reqVO.getChannelCode())
                .likeIfPresent(PayOrderDO::getMerchantOrderId, reqVO.getMerchantOrderId())
                .likeIfPresent(PayOrderDO::getChannelOrderNo, reqVO.getChannelOrderNo())
                .likeIfPresent(PayOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(PayOrderDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PayOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PayOrderDO::getId));

    }

    default Long selectCountByAppId(Long appId) {
        return selectCount(PayOrderDO::getAppId, appId);
    }

    default PayOrderDO selectByAppIdAndMerchantOrderId(Long appId, String merchantOrderId) {
        return selectOne(PayOrderDO::getAppId, appId,
                PayOrderDO::getMerchantOrderId, merchantOrderId);
    }

    default int updateByIdAndStatus(Long id, Integer status, PayOrderDO update) {
        return update(update, new LambdaQueryWrapper<PayOrderDO>()
                .eq(PayOrderDO::getId, id).eq(PayOrderDO::getStatus, status));
    }

    default List<PayOrderDO> selectListByStatusAndExpireTimeLt(Integer status, LocalDateTime expireTime) {
        return selectList(new LambdaQueryWrapper<PayOrderDO>()
                .eq(PayOrderDO::getStatus, status)
                .lt(PayOrderDO::getExpireTime, expireTime));
    }





    default Long selectCount(Long appId, Integer status) {
        return selectCount(new LambdaQueryWrapper<PayOrderDO>()
                .eq(PayOrderDO::getAppId, appId)
                .in(PayOrderDO::getStatus, status));
    }


}
