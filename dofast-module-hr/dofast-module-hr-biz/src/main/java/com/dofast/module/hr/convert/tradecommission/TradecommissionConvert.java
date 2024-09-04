package com.dofast.module.hr.convert.tradecommission;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.tradecommission.vo.*;
import com.dofast.module.hr.dal.dataobject.tradecommission.TradecommissionDO;

/**
 * 工资提成 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface TradecommissionConvert {

    TradecommissionConvert INSTANCE = Mappers.getMapper(TradecommissionConvert.class);

    TradecommissionDO convert(TradecommissionCreateReqVO bean);

    TradecommissionDO convert(TradecommissionUpdateReqVO bean);

    TradecommissionRespVO convert(TradecommissionDO bean);

    List<TradecommissionRespVO> convertList(List<TradecommissionDO> list);

    PageResult<TradecommissionRespVO> convertPage(PageResult<TradecommissionDO> page);

    List<TradecommissionExcelVO> convertList02(List<TradecommissionDO> list);

}
