package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashDepositorDO;

/**
 * 资金账号 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashDepositorConvert {

    CashDepositorConvert INSTANCE = Mappers.getMapper(CashDepositorConvert.class);

    CashDepositorDO convert(CashDepositorCreateReqVO bean);

    CashDepositorDO convert(CashDepositorUpdateReqVO bean);

    CashDepositorRespVO convert(CashDepositorDO bean);

    List<CashDepositorRespVO> convertList(List<CashDepositorDO> list);

    PageResult<CashDepositorRespVO> convertPage(PageResult<CashDepositorDO> page);

    List<CashDepositorExcelVO> convertList02(List<CashDepositorDO> list);

}
