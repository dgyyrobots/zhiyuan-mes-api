package com.dofast.module.finance.convert.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;

/**
 * 财务流水 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CashTradeConvert {

    CashTradeConvert INSTANCE = Mappers.getMapper(CashTradeConvert.class);

    CashTradeDO convert(CashTradeCreateReqVO bean);

    CashTradeDO convert(CashTradeUpdateReqVO bean);

    CashTradeDO convert(CashTradeBpmCreateReqVO been);

    CashTradeRespVO convert(CashTradeDO bean);

    List<CashTradeRespVO> convertList(List<CashTradeDO> list);

    PageResult<CashTradeRespVO> convertPage(PageResult<CashTradeDO> page);

    List<CashTradeExcelVO> convertList02(List<CashTradeDO> list);

    CashTradeBpmRespVO convertBpm(CashTradeDO cashTrade);

    PageResult<CashTradeBpmRespVO> convertPageBpm(PageResult<CashTradeDO> pageResult);
}
