package com.dofast.module.pro.dal.mysql.feedback;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.feedback.vo.*;

/**
 * 生产报工记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FeedbackMapper extends BaseMapperX<FeedbackDO> {

    default PageResult<FeedbackDO> selectPage(FeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeedbackDO>()
                .eqIfPresent(FeedbackDO::getFeedbackType, reqVO.getFeedbackType())
                .likeIfPresent(FeedbackDO::getFeedbackCode, reqVO.getFeedbackCode())
                .eqIfPresent(FeedbackDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(FeedbackDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(FeedbackDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(FeedbackDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(FeedbackDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(FeedbackDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(FeedbackDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(FeedbackDO::getProcessCode, reqVO.getProcessCode())
                .likeIfPresent(FeedbackDO::getProcessName, reqVO.getProcessName())
                .eqIfPresent(FeedbackDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(FeedbackDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(FeedbackDO::getItemId, reqVO.getItemId())
                .eqIfPresent(FeedbackDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(FeedbackDO::getItemName, reqVO.getItemName())
                .eqIfPresent(FeedbackDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(FeedbackDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(FeedbackDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(FeedbackDO::getQuantityFeedback, reqVO.getQuantityFeedback())
                //.eqIfPresent(FeedbackDO::getQuantityQualified, reqVO.getQuantityQualified())
                .gtIfPresent(FeedbackDO::getQuantityQualified, 0)
                .eqIfPresent(FeedbackDO::getQuantityUnquanlified, reqVO.getQuantityUnquanlified())
                .likeIfPresent(FeedbackDO::getUserName, reqVO.getUserName())
                .likeIfPresent(FeedbackDO::getNickName, reqVO.getNickName())
                .eqIfPresent(FeedbackDO::getFeedbackChannel, reqVO.getFeedbackChannel())
                .betweenIfPresent(FeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(FeedbackDO::getRecordUser, reqVO.getRecordUser())
                .eqIfPresent(FeedbackDO::getRecordNick, reqVO.getRecordNick())
                .eqIfPresent(FeedbackDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FeedbackDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeedbackDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(FeedbackDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(FeedbackDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(FeedbackDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(FeedbackDO::getReportFee, reqVO.getReportFee())
                .eqIfPresent(FeedbackDO::getPayFee, reqVO.getPayFee())
                .eqIfPresent(FeedbackDO::getBatchCode, reqVO.getBatchCode())
                .betweenIfPresent(FeedbackDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeedbackDO::getDefectId, reqVO.getDefectId())
                .eqIfPresent(FeedbackDO::getShiftInfo, reqVO.getShiftInfo())
                .eqIfPresent(FeedbackDO::getOriginCode, reqVO.getOriginCode())
                .eqIfPresent(FeedbackDO::getMachineryId, reqVO.getMachineryId())
                .eqIfPresent(FeedbackDO::getMachineryName, reqVO.getMachineryName())
                .eqIfPresent(FeedbackDO::getMachineryCode, reqVO.getMachineryCode())
                .eqIfPresent(FeedbackDO::getErpBatchCode, reqVO.getErpBatchCode())
                .orderByDesc(FeedbackDO::getId));
    }

    default List<FeedbackDO> selectList(FeedbackExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FeedbackDO>()
                .eqIfPresent(FeedbackDO::getFeedbackType, reqVO.getFeedbackType())
                .likeIfPresent(FeedbackDO::getFeedbackCode, reqVO.getFeedbackCode())
                .eqIfPresent(FeedbackDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(FeedbackDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(FeedbackDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(FeedbackDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(FeedbackDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(FeedbackDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(FeedbackDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(FeedbackDO::getProcessCode, reqVO.getProcessCode())
                .likeIfPresent(FeedbackDO::getProcessName, reqVO.getProcessName())
                .eqIfPresent(FeedbackDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(FeedbackDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(FeedbackDO::getItemId, reqVO.getItemId())
                .eqIfPresent(FeedbackDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(FeedbackDO::getItemName, reqVO.getItemName())
                .eqIfPresent(FeedbackDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(FeedbackDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(FeedbackDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(FeedbackDO::getQuantityFeedback, reqVO.getQuantityFeedback())
                .eqIfPresent(FeedbackDO::getQuantityQualified, reqVO.getQuantityQualified())
                .eqIfPresent(FeedbackDO::getQuantityUnquanlified, reqVO.getQuantityUnquanlified())
                .likeIfPresent(FeedbackDO::getUserName, reqVO.getUserName())
                .likeIfPresent(FeedbackDO::getNickName, reqVO.getNickName())
                .eqIfPresent(FeedbackDO::getFeedbackChannel, reqVO.getFeedbackChannel())
                .betweenIfPresent(FeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(FeedbackDO::getRecordUser, reqVO.getRecordUser())
                .eqIfPresent(FeedbackDO::getRecordNick, reqVO.getRecordNick())
                .eqIfPresent(FeedbackDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FeedbackDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeedbackDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(FeedbackDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(FeedbackDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(FeedbackDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(FeedbackDO::getReportFee, reqVO.getReportFee())
                .eqIfPresent(FeedbackDO::getPayFee, reqVO.getPayFee())
                .eqIfPresent(FeedbackDO::getBatchCode, reqVO.getBatchCode())
                .betweenIfPresent(FeedbackDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeedbackDO::getDefectId, reqVO.getDefectId())
                .eqIfPresent(FeedbackDO::getShiftInfo, reqVO.getShiftInfo())
                .eqIfPresent(FeedbackDO::getOriginCode, reqVO.getOriginCode())
                .eqIfPresent(FeedbackDO::getMachineryId, reqVO.getMachineryId())
                .eqIfPresent(FeedbackDO::getMachineryName, reqVO.getMachineryName())
                .eqIfPresent(FeedbackDO::getMachineryCode, reqVO.getMachineryCode())
                .eqIfPresent(FeedbackDO::getErpBatchCode, reqVO.getErpBatchCode())
                .orderByDesc(FeedbackDO::getId));
    }


    default List<FeedbackDO> getListByTaskId(Long taskId){
        return selectList(new LambdaQueryWrapperX<FeedbackDO>()
                .eq(FeedbackDO::getTaskId,taskId)
                .orderByDesc(FeedbackDO::getId));
    }

    public List<Map<String, Object>> getCapacity();



}
