package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;

/**
 * 发票信息 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashInvoiceConvert {

    CashInvoiceConvert INSTANCE = Mappers.getMapper(CashInvoiceConvert.class);

    CashInvoiceDO convert(CashInvoiceCreateReqVO bean);

    CashInvoiceDO convert(CashInvoiceBpmCreateReqVO been);

    CashInvoiceDO convert(CashInvoiceUpdateReqVO bean);

    CashInvoiceRespVO convert(CashInvoiceDO bean);

    List<CashInvoiceRespVO> convertList(List<CashInvoiceDO> list);

    PageResult<CashInvoiceRespVO> convertPage(PageResult<CashInvoiceDO> page);

    List<CashInvoiceExcelVO> convertList02(List<CashInvoiceDO> list);

    CashInvoiceBpmRespVO convertBpm(CashInvoiceDO cashInvoiceDO);

    PageResult<CashInvoiceBpmRespVO> convertPageBpm(PageResult<CashInvoiceDO> pageResult);
}
