package com.dofast.module.pro.dal.mysql.transconsume;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.transconsume.TransConsumeDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.transconsume.vo.*;

/**
 * 物料消耗记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransConsumeMapper extends BaseMapperX<TransConsumeDO> {

    default PageResult<TransConsumeDO> selectPage(TransConsumePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransConsumeDO>()
                .eqIfPresent(TransConsumeDO::getTransOrderId, reqVO.getTransOrderId())
                .eqIfPresent(TransConsumeDO::getTransOrderCode, reqVO.getTransOrderCode())
                .eqIfPresent(TransConsumeDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TransConsumeDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(TransConsumeDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(TransConsumeDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransConsumeDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransConsumeDO::getSourceDocId, reqVO.getSourceDocId())
                .eqIfPresent(TransConsumeDO::getSourceDocCode, reqVO.getSourceDocCode())
                .eqIfPresent(TransConsumeDO::getSourceDocType, reqVO.getSourceDocType())
                .eqIfPresent(TransConsumeDO::getSourceLineId, reqVO.getSourceLineId())
                .eqIfPresent(TransConsumeDO::getSourceBatchCode, reqVO.getSourceBatchCode())
                .eqIfPresent(TransConsumeDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransConsumeDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransConsumeDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransConsumeDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransConsumeDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransConsumeDO::getQuantityConsumed, reqVO.getQuantityConsumed())
                .eqIfPresent(TransConsumeDO::getConsumeDate, reqVO.getConsumeDate())
                .eqIfPresent(TransConsumeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransConsumeDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransConsumeDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransConsumeDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransConsumeDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(TransConsumeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransConsumeDO::getId));
    }

    default List<TransConsumeDO> selectList(TransConsumeListVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransConsumeDO>()
                .eqIfPresent(TransConsumeDO::getTransOrderId, reqVO.getTransOrderId())
                .eqIfPresent(TransConsumeDO::getTransOrderCode, reqVO.getTransOrderCode())
                .eqIfPresent(TransConsumeDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TransConsumeDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(TransConsumeDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(TransConsumeDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransConsumeDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransConsumeDO::getSourceDocId, reqVO.getSourceDocId())
                .eqIfPresent(TransConsumeDO::getSourceDocCode, reqVO.getSourceDocCode())
                .eqIfPresent(TransConsumeDO::getSourceDocType, reqVO.getSourceDocType())
                .eqIfPresent(TransConsumeDO::getSourceLineId, reqVO.getSourceLineId())
                .eqIfPresent(TransConsumeDO::getSourceBatchCode, reqVO.getSourceBatchCode())
                .eqIfPresent(TransConsumeDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransConsumeDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransConsumeDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransConsumeDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransConsumeDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransConsumeDO::getQuantityConsumed, reqVO.getQuantityConsumed())
                .eqIfPresent(TransConsumeDO::getConsumeDate, reqVO.getConsumeDate())
                .eqIfPresent(TransConsumeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransConsumeDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransConsumeDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransConsumeDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransConsumeDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(TransConsumeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransConsumeDO::getId));
    }

    default List<TransConsumeDO> selectList(TransConsumeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransConsumeDO>()
                .eqIfPresent(TransConsumeDO::getTransOrderId, reqVO.getTransOrderId())
                .eqIfPresent(TransConsumeDO::getTransOrderCode, reqVO.getTransOrderCode())
                .eqIfPresent(TransConsumeDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TransConsumeDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(TransConsumeDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(TransConsumeDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransConsumeDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransConsumeDO::getSourceDocId, reqVO.getSourceDocId())
                .eqIfPresent(TransConsumeDO::getSourceDocCode, reqVO.getSourceDocCode())
                .eqIfPresent(TransConsumeDO::getSourceDocType, reqVO.getSourceDocType())
                .eqIfPresent(TransConsumeDO::getSourceLineId, reqVO.getSourceLineId())
                .eqIfPresent(TransConsumeDO::getSourceBatchCode, reqVO.getSourceBatchCode())
                .eqIfPresent(TransConsumeDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransConsumeDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransConsumeDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransConsumeDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransConsumeDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransConsumeDO::getQuantityConsumed, reqVO.getQuantityConsumed())
                .eqIfPresent(TransConsumeDO::getConsumeDate, reqVO.getConsumeDate())
                .eqIfPresent(TransConsumeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransConsumeDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransConsumeDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransConsumeDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransConsumeDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(TransConsumeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransConsumeDO::getId));
    }

}
