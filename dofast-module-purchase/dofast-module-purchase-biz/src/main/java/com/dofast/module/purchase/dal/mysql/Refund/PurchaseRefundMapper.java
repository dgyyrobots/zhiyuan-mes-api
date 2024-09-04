package com.dofast.module.purchase.dal.mysql.refund;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmPageReqVO;
import com.dofast.module.purchase.dal.dataobject.refund.RefundDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.purchase.controller.admin.refund.vo.*;

/**
 * 采购退货 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface PurchaseRefundMapper extends BaseMapperX<RefundDO> {

    default PageResult<RefundDO> selectPage(RefundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RefundDO>()
                .eqIfPresent(RefundDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(RefundDO::getPurchaseOrderNum, reqVO.getPurchaseOrderNum())
                .eqIfPresent(RefundDO::getShipmentNum, reqVO.getShipmentNum())
                .eqIfPresent(RefundDO::getReturnBonus, reqVO.getReturnBonus())
                .eqIfPresent(RefundDO::getIsReturn, reqVO.getIsReturn())
                .eqIfPresent(RefundDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(RefundDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(RefundDO::getSupplieruser, reqVO.getSupplieruser())
                .eqIfPresent(RefundDO::getSupplierphone, reqVO.getSupplierphone())
                .betweenIfPresent(RefundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RefundDO::getId));
    }

    default List<RefundDO> selectList(RefundExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RefundDO>()
                .eqIfPresent(RefundDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(RefundDO::getPurchaseOrderNum, reqVO.getPurchaseOrderNum())
                .eqIfPresent(RefundDO::getShipmentNum, reqVO.getShipmentNum())
                .eqIfPresent(RefundDO::getReturnBonus, reqVO.getReturnBonus())
                .eqIfPresent(RefundDO::getIsReturn, reqVO.getIsReturn())
                .eqIfPresent(RefundDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(RefundDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(RefundDO::getSupplieruser, reqVO.getSupplieruser())
                .eqIfPresent(RefundDO::getSupplierphone, reqVO.getSupplierphone())
                .betweenIfPresent(RefundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RefundDO::getId));
    }

    default PageResult<RefundDO> selectPage(String userId,RefundBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RefundDO>()
                .eqIfPresent(RefundDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(RefundDO::getPurchaseOrderNum, reqVO.getPurchaseOrderNum())
                .eqIfPresent(RefundDO::getShipmentNum, reqVO.getShipmentNum())
                .eqIfPresent(RefundDO::getReturnBonus, reqVO.getReturnBonus())
                .eqIfPresent(RefundDO::getIsReturn, reqVO.getIsReturn())
                .eqIfPresent(RefundDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(RefundDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(RefundDO::getSupplieruser, reqVO.getSupplieruser())
                .eqIfPresent(RefundDO::getSupplierphone, reqVO.getSupplierphone())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(RefundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RefundDO::getId));
    }

}
