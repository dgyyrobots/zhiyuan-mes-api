package com.dofast.module.sales.dal.mysql.orderafter;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmPageReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmUpdateReqVO;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.sales.controller.admin.orderafter.vo.*;

/**
 * 售后流程表单 Mapper
 *
 * @author a1
 */
@Mapper
public interface OrderAfterMapper extends BaseMapperX<OrderAfterDO> {

    default PageResult<OrderAfterDO> selectPage(OrderAfterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderAfterDO>()
                .eqIfPresent(OrderAfterDO::getAssociatedBusiness, reqVO.getAssociatedBusiness())
                .eqIfPresent(OrderAfterDO::getBusinessData, reqVO.getBusinessData())
                .eqIfPresent(OrderAfterDO::getAssociatedObjects, reqVO.getAssociatedObjects())
                .likeIfPresent(OrderAfterDO::getObjectName, reqVO.getObjectName())
                .eqIfPresent(OrderAfterDO::getAssociatedStores, reqVO.getAssociatedStores())
                .eqIfPresent(OrderAfterDO::getAssociatedRepository, reqVO.getAssociatedRepository())
                .eqIfPresent(OrderAfterDO::getAssociatedAmounts, reqVO.getAssociatedAmounts())
                .eqIfPresent(OrderAfterDO::getTransactionCategory, reqVO.getTransactionCategory())
                .eqIfPresent(OrderAfterDO::getPriority, reqVO.getPriority())
                .eqIfPresent(OrderAfterDO::getAddCopy, reqVO.getAddCopy())
                .eqIfPresent(OrderAfterDO::getMainPersonResponsible, reqVO.getMainPersonResponsible())
                .eqIfPresent(OrderAfterDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(OrderAfterDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderAfterDO::getTransactionTitle, reqVO.getTransactionTitle())
                .eqIfPresent(OrderAfterDO::getTransactionContent, reqVO.getTransactionContent())
                .eqIfPresent(OrderAfterDO::getPicture, reqVO.getPicture())
                .eqIfPresent(OrderAfterDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderAfterDO::getProcessInstanceId, reqVO.getProcessInstanceId())
                .betweenIfPresent(OrderAfterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderAfterDO::getId));
    }

    default List<OrderAfterDO> selectList(OrderAfterExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderAfterDO>()
                .eqIfPresent(OrderAfterDO::getAssociatedBusiness, reqVO.getAssociatedBusiness())
                .eqIfPresent(OrderAfterDO::getBusinessData, reqVO.getBusinessData())
                .eqIfPresent(OrderAfterDO::getAssociatedObjects, reqVO.getAssociatedObjects())
                .likeIfPresent(OrderAfterDO::getObjectName, reqVO.getObjectName())
                .eqIfPresent(OrderAfterDO::getAssociatedStores, reqVO.getAssociatedStores())
                .eqIfPresent(OrderAfterDO::getAssociatedRepository, reqVO.getAssociatedRepository())
                .eqIfPresent(OrderAfterDO::getAssociatedAmounts, reqVO.getAssociatedAmounts())
                .eqIfPresent(OrderAfterDO::getTransactionCategory, reqVO.getTransactionCategory())
                .eqIfPresent(OrderAfterDO::getPriority, reqVO.getPriority())
                .eqIfPresent(OrderAfterDO::getAddCopy, reqVO.getAddCopy())
                .eqIfPresent(OrderAfterDO::getMainPersonResponsible, reqVO.getMainPersonResponsible())
                .eqIfPresent(OrderAfterDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(OrderAfterDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderAfterDO::getTransactionTitle, reqVO.getTransactionTitle())
                .eqIfPresent(OrderAfterDO::getTransactionContent, reqVO.getTransactionContent())
                .eqIfPresent(OrderAfterDO::getPicture, reqVO.getPicture())
                .eqIfPresent(OrderAfterDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderAfterDO::getProcessInstanceId, reqVO.getProcessInstanceId())
                .betweenIfPresent(OrderAfterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderAfterDO::getId));
    }

    default OrderAfterDO getSelectOne(OrderAfterDO reqVO){
        return selectOne(new LambdaQueryWrapperX<OrderAfterDO>()
                .eqIfPresent(OrderAfterDO::getAssociatedBusiness, reqVO.getAssociatedBusiness())
                .eqIfPresent(OrderAfterDO::getBusinessData, reqVO.getBusinessData())
                .eqIfPresent(OrderAfterDO::getAssociatedObjects, reqVO.getAssociatedObjects())
                .likeIfPresent(OrderAfterDO::getObjectName, reqVO.getObjectName())
                .eqIfPresent(OrderAfterDO::getAssociatedStores, reqVO.getAssociatedStores())
                .eqIfPresent(OrderAfterDO::getAssociatedRepository, reqVO.getAssociatedRepository())
                .eqIfPresent(OrderAfterDO::getAssociatedAmounts, reqVO.getAssociatedAmounts())
                .eqIfPresent(OrderAfterDO::getTransactionCategory, reqVO.getTransactionCategory())
                .eqIfPresent(OrderAfterDO::getPriority, reqVO.getPriority())
                .eqIfPresent(OrderAfterDO::getAddCopy, reqVO.getAddCopy())
                .eqIfPresent(OrderAfterDO::getMainPersonResponsible, reqVO.getMainPersonResponsible())
                .eqIfPresent(OrderAfterDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(OrderAfterDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderAfterDO::getTransactionTitle, reqVO.getTransactionTitle())
                .eqIfPresent(OrderAfterDO::getTransactionContent, reqVO.getTransactionContent())
                .eqIfPresent(OrderAfterDO::getPicture, reqVO.getPicture())
                .eqIfPresent(OrderAfterDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderAfterDO::getProcessInstanceId, reqVO.getProcessInstanceId()));
    }


    default PageResult<OrderAfterDO> selectPageBpm(OrderAfterBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderAfterDO>()
                .eqIfPresent(OrderAfterDO::getAssociatedBusiness, reqVO.getAssociatedBusiness())
                .eqIfPresent(OrderAfterDO::getBusinessData, reqVO.getBusinessData())
                .eqIfPresent(OrderAfterDO::getAssociatedObjects, reqVO.getAssociatedObjects())
                .likeIfPresent(OrderAfterDO::getObjectName, reqVO.getObjectName())
                .eqIfPresent(OrderAfterDO::getAssociatedStores, reqVO.getAssociatedStores())
                .eqIfPresent(OrderAfterDO::getAssociatedRepository, reqVO.getAssociatedRepository())
                .eqIfPresent(OrderAfterDO::getAssociatedAmounts, reqVO.getAssociatedAmounts())
                .eqIfPresent(OrderAfterDO::getTransactionCategory, reqVO.getTransactionCategory())
                .eqIfPresent(OrderAfterDO::getPriority, reqVO.getPriority())
                .eqIfPresent(OrderAfterDO::getAddCopy, reqVO.getAddCopy())
                .eqIfPresent(OrderAfterDO::getMainPersonResponsible, reqVO.getMainPersonResponsible())
                .eqIfPresent(OrderAfterDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(OrderAfterDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderAfterDO::getTransactionTitle, reqVO.getTransactionTitle())
                .eqIfPresent(OrderAfterDO::getTransactionContent, reqVO.getTransactionContent())
                .eqIfPresent(OrderAfterDO::getPicture, reqVO.getPicture())
                .eqIfPresent(OrderAfterDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderAfterDO::getProcessInstanceId, reqVO.getProcessInstanceId())
                .betweenIfPresent(OrderAfterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderAfterDO::getId));
    }



}
