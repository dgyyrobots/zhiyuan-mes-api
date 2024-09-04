package com.dofast.module.finance.dal.mysql.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 发票信息 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashInvoiceMapper extends BaseMapperX<CashInvoiceDO> {

    default PageResult<CashInvoiceDO> selectPage(CashInvoicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashInvoiceDO>()
                .eqIfPresent(CashInvoiceDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(CashInvoiceDO::getSn, reqVO.getSn())
                .eqIfPresent(CashInvoiceDO::getCompany, reqVO.getCompany())
                .eqIfPresent(CashInvoiceDO::getCustomer, reqVO.getCustomer())
                .eqIfPresent(CashInvoiceDO::getContract, reqVO.getContract())
                .eqIfPresent(CashInvoiceDO::getContact, reqVO.getContact())
                .eqIfPresent(CashInvoiceDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CashInvoiceDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashInvoiceDO::getKind, reqVO.getKind())
                .eqIfPresent(CashInvoiceDO::getType, reqVO.getType())
                .eqIfPresent(CashInvoiceDO::getSaleType, reqVO.getSaleType())
                .eqIfPresent(CashInvoiceDO::getTaxRate, reqVO.getTaxRate())
                .eqIfPresent(CashInvoiceDO::getInvoiceTitle, reqVO.getInvoiceTitle())
                .eqIfPresent(CashInvoiceDO::getTaxNumber, reqVO.getTaxNumber())
                .eqIfPresent(CashInvoiceDO::getRegistedAddress, reqVO.getRegistedAddress())
                .eqIfPresent(CashInvoiceDO::getPhone, reqVO.getPhone())
                .likeIfPresent(CashInvoiceDO::getBankName, reqVO.getBankName())
                .eqIfPresent(CashInvoiceDO::getBankAccount, reqVO.getBankAccount())
                .eqIfPresent(CashInvoiceDO::getExpress, reqVO.getExpress())
                .eqIfPresent(CashInvoiceDO::getWaybill, reqVO.getWaybill())
                .eqIfPresent(CashInvoiceDO::getSendway, reqVO.getSendway())
                .eqIfPresent(CashInvoiceDO::getSendAccount, reqVO.getSendAccount())
                .eqIfPresent(CashInvoiceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CashInvoiceDO::getSubStatus, reqVO.getSubStatus())
                .eqIfPresent(CashInvoiceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CashInvoiceDO::getReceivedBy, reqVO.getReceivedBy())
                .betweenIfPresent(CashInvoiceDO::getReceivedDate, reqVO.getReceivedDate())
                .eqIfPresent(CashInvoiceDO::getCheckedBy, reqVO.getCheckedBy())
                .betweenIfPresent(CashInvoiceDO::getCheckedDate, reqVO.getCheckedDate())
                .eqIfPresent(CashInvoiceDO::getDrawnBy, reqVO.getDrawnBy())
                .betweenIfPresent(CashInvoiceDO::getDrawnDate, reqVO.getDrawnDate())
                .eqIfPresent(CashInvoiceDO::getExpressedBy, reqVO.getExpressedBy())
                .betweenIfPresent(CashInvoiceDO::getExpressedDate, reqVO.getExpressedDate())
                .eqIfPresent(CashInvoiceDO::getTaxRefundedBy, reqVO.getTaxRefundedBy())
                .betweenIfPresent(CashInvoiceDO::getTaxRefundedDate, reqVO.getTaxRefundedDate())
                .betweenIfPresent(CashInvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashInvoiceDO::getId));
    }

    default List<CashInvoiceDO> selectList(CashInvoiceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashInvoiceDO>()
                .eqIfPresent(CashInvoiceDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(CashInvoiceDO::getSn, reqVO.getSn())
                .eqIfPresent(CashInvoiceDO::getCompany, reqVO.getCompany())
                .eqIfPresent(CashInvoiceDO::getCustomer, reqVO.getCustomer())
                .eqIfPresent(CashInvoiceDO::getContract, reqVO.getContract())
                .eqIfPresent(CashInvoiceDO::getContact, reqVO.getContact())
                .eqIfPresent(CashInvoiceDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CashInvoiceDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashInvoiceDO::getKind, reqVO.getKind())
                .eqIfPresent(CashInvoiceDO::getType, reqVO.getType())
                .eqIfPresent(CashInvoiceDO::getSaleType, reqVO.getSaleType())
                .eqIfPresent(CashInvoiceDO::getTaxRate, reqVO.getTaxRate())
                .eqIfPresent(CashInvoiceDO::getInvoiceTitle, reqVO.getInvoiceTitle())
                .eqIfPresent(CashInvoiceDO::getTaxNumber, reqVO.getTaxNumber())
                .eqIfPresent(CashInvoiceDO::getRegistedAddress, reqVO.getRegistedAddress())
                .eqIfPresent(CashInvoiceDO::getPhone, reqVO.getPhone())
                .likeIfPresent(CashInvoiceDO::getBankName, reqVO.getBankName())
                .eqIfPresent(CashInvoiceDO::getBankAccount, reqVO.getBankAccount())
                .eqIfPresent(CashInvoiceDO::getExpress, reqVO.getExpress())
                .eqIfPresent(CashInvoiceDO::getWaybill, reqVO.getWaybill())
                .eqIfPresent(CashInvoiceDO::getSendway, reqVO.getSendway())
                .eqIfPresent(CashInvoiceDO::getSendAccount, reqVO.getSendAccount())
                .eqIfPresent(CashInvoiceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CashInvoiceDO::getSubStatus, reqVO.getSubStatus())
                .eqIfPresent(CashInvoiceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CashInvoiceDO::getReceivedBy, reqVO.getReceivedBy())
                .betweenIfPresent(CashInvoiceDO::getReceivedDate, reqVO.getReceivedDate())
                .eqIfPresent(CashInvoiceDO::getCheckedBy, reqVO.getCheckedBy())
                .betweenIfPresent(CashInvoiceDO::getCheckedDate, reqVO.getCheckedDate())
                .eqIfPresent(CashInvoiceDO::getDrawnBy, reqVO.getDrawnBy())
                .betweenIfPresent(CashInvoiceDO::getDrawnDate, reqVO.getDrawnDate())
                .eqIfPresent(CashInvoiceDO::getExpressedBy, reqVO.getExpressedBy())
                .betweenIfPresent(CashInvoiceDO::getExpressedDate, reqVO.getExpressedDate())
                .eqIfPresent(CashInvoiceDO::getTaxRefundedBy, reqVO.getTaxRefundedBy())
                .betweenIfPresent(CashInvoiceDO::getTaxRefundedDate, reqVO.getTaxRefundedDate())
                .betweenIfPresent(CashInvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashInvoiceDO::getId));
    }

    default PageResult<CashInvoiceDO> selectPage(String userId, CashInvoiceBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashInvoiceDO>()
                .eqIfPresent(CashInvoiceDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(CashInvoiceDO::getSn, reqVO.getSn())
                .eqIfPresent(CashInvoiceDO::getCompany, reqVO.getCompany())
                .eqIfPresent(CashInvoiceDO::getCustomer, reqVO.getCustomer())
                .eqIfPresent(CashInvoiceDO::getContract, reqVO.getContract())
                .eqIfPresent(CashInvoiceDO::getContact, reqVO.getContact())
                .eqIfPresent(CashInvoiceDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CashInvoiceDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashInvoiceDO::getKind, reqVO.getKind())
                .eqIfPresent(CashInvoiceDO::getType, reqVO.getType())
                .eqIfPresent(CashInvoiceDO::getSaleType, reqVO.getSaleType())
                .eqIfPresent(CashInvoiceDO::getTaxRate, reqVO.getTaxRate())
                .eqIfPresent(CashInvoiceDO::getInvoiceTitle, reqVO.getInvoiceTitle())
                .eqIfPresent(CashInvoiceDO::getTaxNumber, reqVO.getTaxNumber())
                .eqIfPresent(CashInvoiceDO::getRegistedAddress, reqVO.getRegistedAddress())
                .eqIfPresent(CashInvoiceDO::getPhone, reqVO.getPhone())
                .likeIfPresent(CashInvoiceDO::getBankName, reqVO.getBankName())
                .eqIfPresent(CashInvoiceDO::getBankAccount, reqVO.getBankAccount())
                .eqIfPresent(CashInvoiceDO::getExpress, reqVO.getExpress())
                .eqIfPresent(CashInvoiceDO::getWaybill, reqVO.getWaybill())
                .eqIfPresent(CashInvoiceDO::getSendway, reqVO.getSendway())
                .eqIfPresent(CashInvoiceDO::getSendAccount, reqVO.getSendAccount())
                .eqIfPresent(CashInvoiceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CashInvoiceDO::getSubStatus, reqVO.getSubStatus())
                .eqIfPresent(CashInvoiceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CashInvoiceDO::getReceivedBy, reqVO.getReceivedBy())
                .betweenIfPresent(CashInvoiceDO::getReceivedDate, reqVO.getReceivedDate())
                .eqIfPresent(CashInvoiceDO::getCheckedBy, reqVO.getCheckedBy())
                .betweenIfPresent(CashInvoiceDO::getCheckedDate, reqVO.getCheckedDate())
                .eqIfPresent(CashInvoiceDO::getDrawnBy, reqVO.getDrawnBy())
                .betweenIfPresent(CashInvoiceDO::getDrawnDate, reqVO.getDrawnDate())
                .eqIfPresent(CashInvoiceDO::getExpressedBy, reqVO.getExpressedBy())
                .betweenIfPresent(CashInvoiceDO::getExpressedDate, reqVO.getExpressedDate())
                .eqIfPresent(CashInvoiceDO::getTaxRefundedBy, reqVO.getTaxRefundedBy())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(CashInvoiceDO::getTaxRefundedDate, reqVO.getTaxRefundedDate())
                .betweenIfPresent(CashInvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashInvoiceDO::getId));
    }

}
