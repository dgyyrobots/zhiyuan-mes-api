package com.dofast.module.purchase.convert.retreatOrder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.dal.dataobject.retreatOrder.RetreatOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.*;

/**
 * ERP仓退单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface RetreatOrderConvert {

    RetreatOrderConvert INSTANCE = Mappers.getMapper(RetreatOrderConvert.class);

    RetreatOrderDO convert(RetreatOrderCreateReqVO bean);

    RetreatOrderDO convert(RetreatOrderUpdateReqVO bean);

    RetreatOrderRespVO convert(RetreatOrderDO bean);

    List<RetreatOrderRespVO> convertList(List<RetreatOrderDO> list);

    PageResult<RetreatOrderRespVO> convertPage(PageResult<RetreatOrderDO> page);

    List<RetreatOrderExcelVO> convertList02(List<RetreatOrderDO> list);

}
