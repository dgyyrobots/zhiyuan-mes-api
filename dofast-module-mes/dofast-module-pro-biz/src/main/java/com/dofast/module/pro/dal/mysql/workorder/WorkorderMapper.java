package com.dofast.module.pro.dal.mysql.workorder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.workorder.vo.*;

/**
 * 生产工单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface WorkorderMapper extends BaseMapperX<WorkorderDO> {
    default WorkorderDO checkWorkorderCodeUnique(WorkorderBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<WorkorderDO>().eq(WorkorderDO::getWorkorderCode,baseVO.getWorkorderCode()));
    }

    default PageResult<WorkorderDO> selectPage(WorkorderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WorkorderDO>()
                .eqIfPresent(WorkorderDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(WorkorderDO::getWorkorderName, reqVO.getWorkorderName())
                //.eqIfPresent(WorkorderDO::getOrderSource, reqVO.getOrderSource())
                .eqIfPresent(WorkorderDO::getSourceCode, reqVO.getSourceCode())
                .eqIfPresent(WorkorderDO::getProductId, reqVO.getProductId())
                .eqIfPresent(WorkorderDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(WorkorderDO::getProductName, reqVO.getProductName())
                .eqIfPresent(WorkorderDO::getProductSpc, reqVO.getProductSpc())
                .eqIfPresent(WorkorderDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(WorkorderDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(WorkorderDO::getQuantityProduced, reqVO.getQuantityProduced())
                .eqIfPresent(WorkorderDO::getQuantityChanged, reqVO.getQuantityChanged())
                .eqIfPresent(WorkorderDO::getQuantityScheduled, reqVO.getQuantityScheduled())
                .eqIfPresent(WorkorderDO::getClientId, reqVO.getClientId())
                .eqIfPresent(WorkorderDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(WorkorderDO::getClientName, reqVO.getClientName())
                .eqIfPresent(WorkorderDO::getBatchCode, reqVO.getBatchCode())
                .betweenIfPresent(WorkorderDO::getRequestDate, reqVO.getRequestDate())
                .eqIfPresent(WorkorderDO::getParentId, 0)
                .eqIfPresent(WorkorderDO::getAncestors, reqVO.getAncestors())
                .eqIfPresent(WorkorderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WorkorderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(WorkorderDO::getIsOut, reqVO.getIsOut())
                .eqIfPresent(WorkorderDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(WorkorderDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(WorkorderDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(WorkorderDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(WorkorderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WorkorderDO::getAdjuncts, reqVO.getAdjuncts())
                .eqIfPresent(WorkorderDO::getMixinOrderId, reqVO.getMixinOrderId())
                .notInIfPresent(WorkorderDO::getOrderSource, Collections.singleton("4"))
                .orderByAsc(WorkorderDO::getRequestDate)
                .orderByDesc(WorkorderDO::getProductSpc));
    }
    default List<WorkorderDO> selectList(WorkorderListVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WorkorderDO>()
                .eqIfPresent(WorkorderDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(WorkorderDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(WorkorderDO::getOrderSource, reqVO.getOrderSource())
                .eqIfPresent(WorkorderDO::getSourceCode, reqVO.getSourceCode())
                .eqIfPresent(WorkorderDO::getProductId, reqVO.getProductId())
                .eqIfPresent(WorkorderDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(WorkorderDO::getProductName, reqVO.getProductName())
                .eqIfPresent(WorkorderDO::getProductSpc, reqVO.getProductSpc())
                .eqIfPresent(WorkorderDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(WorkorderDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(WorkorderDO::getQuantityProduced, reqVO.getQuantityProduced())
                .eqIfPresent(WorkorderDO::getQuantityChanged, reqVO.getQuantityChanged())
                .eqIfPresent(WorkorderDO::getQuantityScheduled, reqVO.getQuantityScheduled())
                .eqIfPresent(WorkorderDO::getClientId, reqVO.getClientId())
                .eqIfPresent(WorkorderDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(WorkorderDO::getClientName, reqVO.getClientName())
                .eqIfPresent(WorkorderDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(WorkorderDO::getRequestDate, reqVO.getRequestDate())
                .eqIfPresent(WorkorderDO::getParentId, reqVO.getParentId())
                .eqIfPresent(WorkorderDO::getAncestors, reqVO.getAncestors())
                .eqIfPresent(WorkorderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WorkorderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(WorkorderDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(WorkorderDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(WorkorderDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(WorkorderDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(WorkorderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WorkorderDO::getAdjuncts, reqVO.getAdjuncts())
                .eqIfPresent(WorkorderDO::getIsOut, reqVO.getIsOut())
                .orderByAsc(WorkorderDO::getRequestDate)
                .orderByAsc(WorkorderDO::getProductSpc));
    }


    default List<WorkorderDO> selectList(WorkorderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WorkorderDO>()
                .eqIfPresent(WorkorderDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(WorkorderDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(WorkorderDO::getOrderSource, reqVO.getOrderSource())
                .eqIfPresent(WorkorderDO::getSourceCode, reqVO.getSourceCode())
                .eqIfPresent(WorkorderDO::getProductId, reqVO.getProductId())
                .eqIfPresent(WorkorderDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(WorkorderDO::getProductName, reqVO.getProductName())
                .eqIfPresent(WorkorderDO::getProductSpc, reqVO.getProductSpc())
                .eqIfPresent(WorkorderDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(WorkorderDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(WorkorderDO::getQuantityProduced, reqVO.getQuantityProduced())
                .eqIfPresent(WorkorderDO::getQuantityChanged, reqVO.getQuantityChanged())
                .eqIfPresent(WorkorderDO::getQuantityScheduled, reqVO.getQuantityScheduled())
                .eqIfPresent(WorkorderDO::getClientId, reqVO.getClientId())
                .eqIfPresent(WorkorderDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(WorkorderDO::getClientName, reqVO.getClientName())
                .eqIfPresent(WorkorderDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(WorkorderDO::getRequestDate, reqVO.getRequestDate())
                .eqIfPresent(WorkorderDO::getParentId, reqVO.getParentId())
                .eqIfPresent(WorkorderDO::getAncestors, reqVO.getAncestors())
                .eqIfPresent(WorkorderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WorkorderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(WorkorderDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(WorkorderDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(WorkorderDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(WorkorderDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(WorkorderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WorkorderDO::getAdjuncts, reqVO.getAdjuncts())
                .eqIfPresent(WorkorderDO::getIsOut, reqVO.getIsOut())
                .eqIfPresent(WorkorderDO::getMixinOrderId, reqVO.getMixinOrderId())
                .orderByDesc(WorkorderDO::getId));
    }


    default Long selectCount(WorkorderPageReqVO reqVO) {
        return selectCount(new LambdaQueryWrapperX<WorkorderDO>()
                .eqIfPresent(WorkorderDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(WorkorderDO::getWorkorderName, reqVO.getWorkorderName())
                //.eqIfPresent(WorkorderDO::getOrderSource, reqVO.getOrderSource())
                .eqIfPresent(WorkorderDO::getSourceCode, reqVO.getSourceCode())
                .eqIfPresent(WorkorderDO::getProductId, reqVO.getProductId())
                .eqIfPresent(WorkorderDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(WorkorderDO::getProductName, reqVO.getProductName())
                .eqIfPresent(WorkorderDO::getProductSpc, reqVO.getProductSpc())
                .eqIfPresent(WorkorderDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(WorkorderDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(WorkorderDO::getQuantityProduced, reqVO.getQuantityProduced())
                .eqIfPresent(WorkorderDO::getQuantityChanged, reqVO.getQuantityChanged())
                .eqIfPresent(WorkorderDO::getQuantityScheduled, reqVO.getQuantityScheduled())
                .eqIfPresent(WorkorderDO::getClientId, reqVO.getClientId())
                .eqIfPresent(WorkorderDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(WorkorderDO::getClientName, reqVO.getClientName())
                .eqIfPresent(WorkorderDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(WorkorderDO::getRequestDate, reqVO.getRequestDate())
                .eqIfPresent(WorkorderDO::getParentId, 0)
                .eqIfPresent(WorkorderDO::getAncestors, reqVO.getAncestors())
                .eqIfPresent(WorkorderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WorkorderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(WorkorderDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(WorkorderDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(WorkorderDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(WorkorderDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(WorkorderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WorkorderDO::getAdjuncts, reqVO.getAdjuncts())
                .eqIfPresent(WorkorderDO::getIsOut, reqVO.getIsOut())
                .eqIfPresent(WorkorderDO::getMixinOrderId, reqVO.getMixinOrderId())
                .notInIfPresent(WorkorderDO::getOrderSource, Collections.singleton("4")));

    }

}
