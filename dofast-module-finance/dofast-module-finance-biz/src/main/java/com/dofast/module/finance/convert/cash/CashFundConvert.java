package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmRespVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmCreateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;

/**
 * 财务退款 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashFundConvert {

    CashFundConvert INSTANCE = Mappers.getMapper(CashFundConvert.class);

    CashFundDO convert(CashFundCreateReqVO bean);

    CashFundDO convert(CashFundBpmCreateReqVO bpmCreateReqVO);

    CashFundDO convert(CashFundUpdateReqVO bean);

    CashFundRespVO convert(CashFundDO bean);

    List<CashFundRespVO> convertList(List<CashFundDO> list);

    PageResult<CashFundRespVO> convertPage(PageResult<CashFundDO> page);

    List<CashFundExcelVO> convertList02(List<CashFundDO> list);

    CashFundBpmRespVO convertBpm(CashFundDO fundDO);

    PageResult<CashFundBpmRespVO> convertPageBpm(PageResult<CashFundDO> pageResult);
}
