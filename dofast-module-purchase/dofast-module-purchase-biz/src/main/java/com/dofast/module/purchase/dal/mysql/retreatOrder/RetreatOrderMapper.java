package com.dofast.module.purchase.dal.mysql.retreatOrder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.purchase.dal.dataobject.retreatOrder.RetreatOrderDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.*;

/**
 * ERP仓退单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface RetreatOrderMapper extends BaseMapperX<RetreatOrderDO> {

    default PageResult<RetreatOrderDO> selectPage(RetreatOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RetreatOrderDO>()
                .eqIfPresent(RetreatOrderDO::getRetreatCode, reqVO.getRetreatCode())
                .likeIfPresent(RetreatOrderDO::getRetreatName, reqVO.getRetreatName())
                .likeIfPresent(RetreatOrderDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(RetreatOrderDO::getVendorCode, reqVO.getVendorCode())
                .eqIfPresent(RetreatOrderDO::getRetreatUser, reqVO.getRetreatUser())
                .eqIfPresent(RetreatOrderDO::getRetreatNick, reqVO.getRetreatNick())
                .betweenIfPresent(RetreatOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RetreatOrderDO::getId));
    }

    default List<RetreatOrderDO> selectList(RetreatOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RetreatOrderDO>()
                .eqIfPresent(RetreatOrderDO::getRetreatCode, reqVO.getRetreatCode())
                .likeIfPresent(RetreatOrderDO::getRetreatName, reqVO.getRetreatName())
                .likeIfPresent(RetreatOrderDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(RetreatOrderDO::getVendorCode, reqVO.getVendorCode())
                .eqIfPresent(RetreatOrderDO::getRetreatUser, reqVO.getRetreatUser())
                .eqIfPresent(RetreatOrderDO::getRetreatNick, reqVO.getRetreatNick())
                .betweenIfPresent(RetreatOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RetreatOrderDO::getId));
    }

}
