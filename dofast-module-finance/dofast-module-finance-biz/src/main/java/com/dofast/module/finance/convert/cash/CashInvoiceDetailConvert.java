package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;

/**
 * 财务发票明细 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashInvoiceDetailConvert {

    CashInvoiceDetailConvert INSTANCE = Mappers.getMapper(CashInvoiceDetailConvert.class);

    CashInvoiceDetailDO convert(CashInvoiceDetailCreateReqVO bean);

    CashInvoiceDetailDO convert(CashInvoiceDetailBpmCreateReqVO been);

    CashInvoiceDetailDO convert(CashInvoiceDetailUpdateReqVO bean);

    CashInvoiceDetailRespVO convert(CashInvoiceDetailDO bean);

    List<CashInvoiceDetailRespVO> convertList(List<CashInvoiceDetailDO> list);

    PageResult<CashInvoiceDetailRespVO> convertPage(PageResult<CashInvoiceDetailDO> page);

    List<CashInvoiceDetailExcelVO> convertList02(List<CashInvoiceDetailDO> list);

    CashInvoiceDetailBpmRespVO convertBpm(CashInvoiceDetailDO cashInvoiceDetail);

    PageResult<CashInvoiceDetailBpmRespVO> convertPageBpm(PageResult<CashInvoiceDetailDO> pageResult);
}
