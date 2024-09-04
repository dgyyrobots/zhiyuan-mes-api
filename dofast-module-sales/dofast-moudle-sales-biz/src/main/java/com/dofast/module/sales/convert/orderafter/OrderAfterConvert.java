package com.dofast.module.sales.convert.orderafter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmCreateReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmRespVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.sales.controller.admin.orderafter.vo.*;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;

/**
 * 售后流程表单 Convert
 *
 * @author a1
 */
@Mapper
public interface OrderAfterConvert {

    OrderAfterConvert INSTANCE = Mappers.getMapper(OrderAfterConvert.class);

    OrderAfterDO convert(OrderAfterCreateReqVO bean);

    default OrderAfterDO convert(OrderAfterBpmUpdateReqVO bean){
        if ( bean == null ) {
            return null;
        }

        OrderAfterDO.OrderAfterDOBuilder orderAfterDO = OrderAfterDO.builder();

        orderAfterDO.code( bean.getCode() );
        orderAfterDO.associatedBusiness( bean.getAssociatedBusiness() );
        orderAfterDO.associatedStores( bean.getAssociatedStores() );
        orderAfterDO.associatedRepository( bean.getAssociatedRepository() );
        orderAfterDO.transactionCategory( bean.getTransactionCategory() );
        orderAfterDO.mainPersonResponsible( bean.getMainPersonResponsible() );
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(bean.getDeadline(),df);
        orderAfterDO.deadline(parse);
        orderAfterDO.remark( bean.getRemark() );
        orderAfterDO.transactionTitle( bean.getTransactionTitle() );
        orderAfterDO.transactionContent( bean.getTransactionContent() );
        orderAfterDO.picture( bean.getPicture() );
        orderAfterDO.status( bean.getStatus() );
        orderAfterDO.processInstanceId( bean.getProcessInstanceId() );

        return orderAfterDO.build();
    }

    OrderAfterDO convert(OrderAfterUpdateReqVO bean);

    OrderAfterRespVO convert(OrderAfterDO bean);

    default OrderAfterBpmRespVO convertBpm(OrderAfterDO bean){
        if ( bean == null ) {
            return null;
        }

        OrderAfterBpmRespVO orderAfterBpmRespVO = new OrderAfterBpmRespVO();

        orderAfterBpmRespVO.setCode( bean.getCode() );
        orderAfterBpmRespVO.setAssociatedBusiness( bean.getAssociatedBusiness() );
        orderAfterBpmRespVO.setAssociatedStores( bean.getAssociatedStores() );
        orderAfterBpmRespVO.setAssociatedRepository( bean.getAssociatedRepository() );
        orderAfterBpmRespVO.setTransactionCategory( bean.getTransactionCategory() );
        orderAfterBpmRespVO.setMainPersonResponsible( bean.getMainPersonResponsible() );
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        orderAfterBpmRespVO.setDeadline( df.format(bean.getDeadline()));
        orderAfterBpmRespVO.setRemark( bean.getRemark() );
        orderAfterBpmRespVO.setTransactionTitle( bean.getTransactionTitle() );
        orderAfterBpmRespVO.setTransactionContent( bean.getTransactionContent() );
        orderAfterBpmRespVO.setPicture( bean.getPicture() );
        orderAfterBpmRespVO.setStatus( bean.getStatus() );
        orderAfterBpmRespVO.setProcessInstanceId( bean.getProcessInstanceId() );
        orderAfterBpmRespVO.setId( bean.getId() );
        orderAfterBpmRespVO.setCreateTime( bean.getCreateTime() );

        return orderAfterBpmRespVO;
    }
    List<OrderAfterRespVO> convertList(List<OrderAfterDO> list);

    PageResult<OrderAfterRespVO> convertPage(PageResult<OrderAfterDO> page);

    PageResult<OrderAfterBpmRespVO> convertPageBpm(PageResult<OrderAfterDO> pageResult);

    List<OrderAfterExcelVO> convertList02(List<OrderAfterDO> list);

    default OrderAfterDO convert(OrderAfterBpmCreateReqVO orderAfterBpmCreateReqVO){
        if ( orderAfterBpmCreateReqVO == null ) {
            return null;
        }

        OrderAfterDO.OrderAfterDOBuilder orderAfterDO = OrderAfterDO.builder();

        orderAfterDO.code( orderAfterBpmCreateReqVO.getCode() );
        orderAfterDO.associatedBusiness( orderAfterBpmCreateReqVO.getAssociatedBusiness() );
        orderAfterDO.associatedStores( orderAfterBpmCreateReqVO.getAssociatedStores() );
        orderAfterDO.associatedRepository( orderAfterBpmCreateReqVO.getAssociatedRepository() );
        orderAfterDO.transactionCategory( orderAfterBpmCreateReqVO.getTransactionCategory() );
        orderAfterDO.mainPersonResponsible( orderAfterBpmCreateReqVO.getMainPersonResponsible() );
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(orderAfterBpmCreateReqVO.getDeadline(),df);
        orderAfterDO.deadline(parse);
        orderAfterDO.remark( orderAfterBpmCreateReqVO.getRemark() );
        orderAfterDO.transactionTitle( orderAfterBpmCreateReqVO.getTransactionTitle() );
        orderAfterDO.transactionContent( orderAfterBpmCreateReqVO.getTransactionContent() );
        orderAfterDO.picture( orderAfterBpmCreateReqVO.getPicture() );
        orderAfterDO.status( orderAfterBpmCreateReqVO.getStatus() );
        orderAfterDO.processInstanceId( orderAfterBpmCreateReqVO.getProcessInstanceId() );

        return orderAfterDO.build();
    }

}
