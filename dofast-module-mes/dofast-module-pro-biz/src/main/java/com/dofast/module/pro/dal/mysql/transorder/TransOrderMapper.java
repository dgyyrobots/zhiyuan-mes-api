package com.dofast.module.pro.dal.mysql.transorder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.transorder.vo.*;

/**
 * 流转单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransOrderMapper extends BaseMapperX<TransOrderDO> {

    default PageResult<TransOrderDO> selectPage(TransOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransOrderDO>()
                .eqIfPresent(TransOrderDO::getTransOrderCode, reqVO.getTransOrderCode())
                .eqIfPresent(TransOrderDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TransOrderDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(TransOrderDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(TransOrderDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(TransOrderDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(TransOrderDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(TransOrderDO::getProcessCode, reqVO.getProcessCode())
                .likeIfPresent(TransOrderDO::getProcessName, reqVO.getProcessName())
                .eqIfPresent(TransOrderDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransOrderDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(TransOrderDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(TransOrderDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransOrderDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransOrderDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransOrderDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransOrderDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransOrderDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransOrderDO::getBarcodeUrl, reqVO.getBarcodeUrl())
                .eqIfPresent(TransOrderDO::getQuantityTransfered, reqVO.getQuantityTransfered())
                .eqIfPresent(TransOrderDO::getProduceDate, reqVO.getProduceDate())
                .eqIfPresent(TransOrderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransOrderDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransOrderDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransOrderDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransOrderDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(TransOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransOrderDO::getId));
    }

    default List<TransOrderDO> selectList(TransOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransOrderDO>()
                .eqIfPresent(TransOrderDO::getTransOrderCode, reqVO.getTransOrderCode())
                .eqIfPresent(TransOrderDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TransOrderDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(TransOrderDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(TransOrderDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(TransOrderDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(TransOrderDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(TransOrderDO::getProcessCode, reqVO.getProcessCode())
                .likeIfPresent(TransOrderDO::getProcessName, reqVO.getProcessName())
                .eqIfPresent(TransOrderDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransOrderDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(TransOrderDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(TransOrderDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransOrderDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransOrderDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransOrderDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransOrderDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransOrderDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransOrderDO::getBarcodeUrl, reqVO.getBarcodeUrl())
                .eqIfPresent(TransOrderDO::getQuantityTransfered, reqVO.getQuantityTransfered())
                .eqIfPresent(TransOrderDO::getProduceDate, reqVO.getProduceDate())
                .eqIfPresent(TransOrderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransOrderDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransOrderDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransOrderDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransOrderDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(TransOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransOrderDO::getId));
    }

}
