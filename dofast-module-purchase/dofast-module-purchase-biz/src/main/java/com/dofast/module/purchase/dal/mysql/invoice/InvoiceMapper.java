package com.dofast.module.purchase.dal.mysql.invoice;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmPageReqVO;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.purchase.controller.admin.invoice.vo.*;

/**
 * 采购入库单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface InvoiceMapper extends BaseMapperX<InvoiceDO> {

    default PageResult<InvoiceDO> selectPage(InvoicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InvoiceDO>()
                .eqIfPresent(InvoiceDO::getPurchaseOrderNum, reqVO.getPurchaseOrderNum())
                .eqIfPresent(InvoiceDO::getShipmentNum, reqVO.getShipmentNum())
                .eqIfPresent(InvoiceDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(InvoiceDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(InvoiceDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(InvoiceDO::getIsReturn, reqVO.getIsReturn())
                .eqIfPresent(InvoiceDO::getSupplieruser, reqVO.getSupplieruser())
                .eqIfPresent(InvoiceDO::getSupplierphone, reqVO.getSupplierphone())
                .eqIfPresent(InvoiceDO::getTotalPrice, reqVO.getTotalPrice())
                .eqIfPresent(InvoiceDO::getIsWarehousing, reqVO.getIsWarehousing())
                .betweenIfPresent(InvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InvoiceDO::getId));
    }

    default List<InvoiceDO> selectList(InvoiceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<InvoiceDO>()
                .eqIfPresent(InvoiceDO::getPurchaseOrderNum, reqVO.getPurchaseOrderNum())
                .eqIfPresent(InvoiceDO::getShipmentNum, reqVO.getShipmentNum())
                .eqIfPresent(InvoiceDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(InvoiceDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(InvoiceDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(InvoiceDO::getIsReturn, reqVO.getIsReturn())
                .eqIfPresent(InvoiceDO::getSupplieruser, reqVO.getSupplieruser())
                .eqIfPresent(InvoiceDO::getSupplierphone, reqVO.getSupplierphone())
                .eqIfPresent(InvoiceDO::getTotalPrice, reqVO.getTotalPrice())
                .eqIfPresent(InvoiceDO::getIsWarehousing, reqVO.getIsWarehousing())
                .betweenIfPresent(InvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InvoiceDO::getId));
    }
//    InvoiceBpmPageReqVO

    default PageResult<InvoiceDO> selectPage(String userId,InvoiceBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InvoiceDO>()
                .eqIfPresent(InvoiceDO::getPurchaseOrderNum, reqVO.getPurchaseOrderNum())
                .eqIfPresent(InvoiceDO::getShipmentNum, reqVO.getShipmentNum())
                .eqIfPresent(InvoiceDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(InvoiceDO::getSupplierId, reqVO.getSupplierId())
                .eqIfPresent(InvoiceDO::getPaymentType, reqVO.getPaymentType())
                .eqIfPresent(InvoiceDO::getIsReturn, reqVO.getIsReturn())
                .eqIfPresent(InvoiceDO::getSupplieruser, reqVO.getSupplieruser())
                .eqIfPresent(InvoiceDO::getSupplierphone, reqVO.getSupplierphone())
                .eqIfPresent(InvoiceDO::getTotalPrice, reqVO.getTotalPrice())
                .eqIfPresent(InvoiceDO::getIsWarehousing, reqVO.getIsWarehousing())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(InvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InvoiceDO::getId));
    }
}
