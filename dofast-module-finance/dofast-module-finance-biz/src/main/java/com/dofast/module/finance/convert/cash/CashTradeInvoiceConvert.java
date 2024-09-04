package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeInvoiceDO;

/**
 * 业务发票关联 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashTradeInvoiceConvert {

    CashTradeInvoiceConvert INSTANCE = Mappers.getMapper(CashTradeInvoiceConvert.class);

    CashTradeInvoiceDO convert(CashTradeInvoiceCreateReqVO bean);

    CashTradeInvoiceDO convert(CashTradeInvoiceUpdateReqVO bean);

    CashTradeInvoiceRespVO convert(CashTradeInvoiceDO bean);

    List<CashTradeInvoiceRespVO> convertList(List<CashTradeInvoiceDO> list);

    PageResult<CashTradeInvoiceRespVO> convertPage(PageResult<CashTradeInvoiceDO> page);

    List<CashTradeInvoiceExcelVO> convertList02(List<CashTradeInvoiceDO> list);

}
