package com.dofast.module.purchase.convert.invoice;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.purchase.controller.admin.invoice.vo.*;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;

/**
 * 采购入库单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface InvoiceConvert {

    InvoiceConvert INSTANCE = Mappers.getMapper(InvoiceConvert.class);

    InvoiceDO convert(InvoiceCreateReqVO bean);

    InvoiceDO convert(InvoiceUpdateReqVO bean);

    InvoiceDO convert(InvoiceBpmCreateReqVO bpmCreateReqVO);

    InvoiceRespVO convert(InvoiceDO bean);

    List<InvoiceRespVO> convertList(List<InvoiceDO> list);

    PageResult<InvoiceRespVO> convertPage(PageResult<InvoiceDO> page);

    List<InvoiceExcelVO> convertList02(List<InvoiceDO> list);

    InvoiceBpmRespVO convertBpm(InvoiceDO invoiceDO);

    PageResult<InvoiceBpmRespVO> convertPageBpm(PageResult<InvoiceDO> pageResult);
}
