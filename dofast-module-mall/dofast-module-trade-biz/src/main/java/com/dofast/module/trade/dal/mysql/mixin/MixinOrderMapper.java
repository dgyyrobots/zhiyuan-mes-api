package com.dofast.module.trade.dal.mysql.mixin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmPageReqVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderExportReqVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderPageReqVO;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 销售订单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface MixinOrderMapper extends BaseMapperX<MixinOrderDO> {

    default PageResult<MixinOrderDO> selectPage(MixinOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MixinOrderDO>()
                .inIfPresent(MixinOrderDO::getId, reqVO.getIds())
                .eqIfPresent(MixinOrderDO::getSaleNo, reqVO.getSaleNo())
                .likeIfPresent(MixinOrderDO::getTitle, reqVO.getTitle())
                .likeIfPresent(MixinOrderDO::getSalerName, reqVO.getSalerName())
                .eqIfPresent(MixinOrderDO::getSaler, reqVO.getSaler())
                .eqIfPresent(MixinOrderDO::getStatus,reqVO.getStatus())
                .eqIfPresent(MixinOrderDO::getIsPrint,reqVO.getProductSalseCount())
                .eqIfPresent(MixinOrderDO::getSettlementMethod, reqVO.getSettlementMethod())
                .betweenIfPresent(MixinOrderDO::getEstimatedDeliveryTime, reqVO.getEstimatedDeliveryTime())
                .betweenIfPresent(MixinOrderDO::getPrice, reqVO.getPrice())
                .betweenIfPresent(MixinOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MixinOrderDO::getIsPrint, reqVO.getIsPrint())
                .orderByDesc(MixinOrderDO::getEstimatedDeliveryTime)
                .orderByDesc(MixinOrderDO::getId));
    }

    default PageResult<MixinOrderDO> selectPageDesc(MixinOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MixinOrderDO>()
                .inIfPresent(MixinOrderDO::getId, reqVO.getIds())
                .eqIfPresent(MixinOrderDO::getSaleNo, reqVO.getSaleNo())
                .likeIfPresent(MixinOrderDO::getTitle, reqVO.getTitle())
                .likeIfPresent(MixinOrderDO::getSalerName, reqVO.getSalerName())
                .eqIfPresent(MixinOrderDO::getSaler, reqVO.getSaler())
                .eqIfPresent(MixinOrderDO::getStatus,reqVO.getStatus())
                .eqIfPresent(MixinOrderDO::getIsPrint,reqVO.getProductSalseCount())
                .eqIfPresent(MixinOrderDO::getSettlementMethod, reqVO.getSettlementMethod())
                .betweenIfPresent(MixinOrderDO::getEstimatedDeliveryTime, reqVO.getEstimatedDeliveryTime())
                .betweenIfPresent(MixinOrderDO::getPrice, reqVO.getPrice())
                .betweenIfPresent(MixinOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MixinOrderDO::getIsPrint, reqVO.getIsPrint())
//                .orderByAsc(MixinOrderDO::getEstimatedDeliveryTime)
                .orderByDesc(MixinOrderDO::getEstimatedDeliveryTime)
                .orderByDesc(MixinOrderDO::getId));
    }


    default List<MixinOrderDO> selectList(MixinOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MixinOrderDO>()
                .eqIfPresent(MixinOrderDO::getSaleNo, reqVO.getSaleNo())
                .likeIfPresent(MixinOrderDO::getTitle, reqVO.getTitle())
                .likeIfPresent(MixinOrderDO::getSalerName, reqVO.getSalerName())
                .eqIfPresent(MixinOrderDO::getSaler, reqVO.getSaler())
                .eqIfPresent(MixinOrderDO::getSettlementMethod, reqVO.getSettlementMethod())
                .betweenIfPresent(MixinOrderDO::getEstimatedDeliveryTime, reqVO.getEstimatedDeliveryTime())
                .betweenIfPresent(MixinOrderDO::getPrice, reqVO.getPrice())
                .betweenIfPresent(MixinOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MixinOrderDO::getIsPrint, reqVO.getIsPrint())
                .orderByDesc(MixinOrderDO::getId));
    }

    default Map<String, Object> selectTotalSellPrice(LocalDateTime start, LocalDateTime end){
        QueryWrapper<MixinOrderDO> wrapper = new QueryWrapper<>();
        wrapper.select("SUM(price) AS order_total")
                .between("create_time", start, end);
        Map<String, Object> result = selectMaps(wrapper).get(0);
        return result;
    }


    default PageResult<MixinOrderDO> selectPage(String userId, MixinOrderBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MixinOrderDO>()
                .inIfPresent(MixinOrderDO::getId, reqVO.getIds())
                .eqIfPresent(MixinOrderDO::getSaleNo, reqVO.getSaleNo())
                .likeIfPresent(MixinOrderDO::getTitle, reqVO.getTitle())
                .likeIfPresent(MixinOrderDO::getSalerName, reqVO.getSalerName())
                .eqIfPresent(MixinOrderDO::getSaler, reqVO.getSaler())
                .eqIfPresent(MixinOrderDO::getSettlementMethod, reqVO.getSettlementMethod())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(MixinOrderDO::getEstimatedDeliveryTime, reqVO.getEstimatedDeliveryTime())
                .betweenIfPresent(MixinOrderDO::getPrice, reqVO.getPrice())
                .betweenIfPresent(MixinOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MixinOrderDO::getIsPrint, reqVO.getIsPrint())
                .orderByAsc(MixinOrderDO::getEstimatedDeliveryTime)
                .orderByDesc(MixinOrderDO::getId));
    }

}
