package com.dofast.module.purchase.dal.mysql.order;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmPageReqVO;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.purchase.controller.admin.order.vo.*;

/**
 * 采购订单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(OrderDO::getSupplierContact, reqVO.getSupplierContact())
                .eqIfPresent(OrderDO::getSupplierPhone, reqVO.getSupplierPhone())
                .eqIfPresent(OrderDO::getPurchaseAmount, reqVO.getPurchaseAmount())
                .eqIfPresent(OrderDO::getPoNo, reqVO.getPoNo())
                .eqIfPresent(OrderDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(OrderDO::getReturnGoods, reqVO.getReturnGoods())
                .eqIfPresent(OrderDO::getProcessType, reqVO.getProcessType())
                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

    default List<OrderDO> selectList(OrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(OrderDO::getSupplierContact, reqVO.getSupplierContact())
                .eqIfPresent(OrderDO::getSupplierPhone, reqVO.getSupplierPhone())
                .eqIfPresent(OrderDO::getPurchaseAmount, reqVO.getPurchaseAmount())
                .eqIfPresent(OrderDO::getPoNo, reqVO.getPoNo())
                .eqIfPresent(OrderDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(OrderDO::getReturnGoods, reqVO.getReturnGoods())
                .eqIfPresent(OrderDO::getProcessType, reqVO.getProcessType())
                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

    default PageResult<OrderDO> selectPage(String userId,OrderBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(OrderDO::getSupplierContact, reqVO.getSupplierContact())
                .eqIfPresent(OrderDO::getSupplierPhone, reqVO.getSupplierPhone())
                .eqIfPresent(OrderDO::getPurchaseAmount, reqVO.getPurchaseAmount())
                .eqIfPresent(OrderDO::getPoNo, reqVO.getPoNo())
                .eqIfPresent(OrderDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(OrderDO::getReturnGoods, reqVO.getReturnGoods())
                .eqIfPresent(OrderDO::getProcessType, reqVO.getProcessType())
                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

}
