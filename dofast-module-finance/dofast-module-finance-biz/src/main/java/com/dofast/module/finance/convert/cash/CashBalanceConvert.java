package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashBalanceDO;

/**
 * 现金余额 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashBalanceConvert {

    CashBalanceConvert INSTANCE = Mappers.getMapper(CashBalanceConvert.class);

    CashBalanceDO convert(CashBalanceCreateReqVO bean);

    CashBalanceDO convert(CashBalanceUpdateReqVO bean);

    CashBalanceRespVO convert(CashBalanceDO bean);

    List<CashBalanceRespVO> convertList(List<CashBalanceDO> list);

    PageResult<CashBalanceRespVO> convertPage(PageResult<CashBalanceDO> page);

    List<CashBalanceExcelVO> convertList02(List<CashBalanceDO> list);

}
