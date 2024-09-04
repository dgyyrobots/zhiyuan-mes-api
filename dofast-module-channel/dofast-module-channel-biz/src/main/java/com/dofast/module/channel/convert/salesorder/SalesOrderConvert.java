package com.dofast.module.channel.convert.salesorder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.channel.controller.admin.salesorder.vo.*;
import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;

/**
 * 销售订单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface SalesOrderConvert {

    SalesOrderConvert INSTANCE = Mappers.getMapper(SalesOrderConvert.class);

    SalesOrderDO convert(SalesOrderCreateReqVO bean);

    SalesOrderDO convert(SalesOrderUpdateReqVO bean);

    SalesOrderRespVO convert(SalesOrderDO bean);

    List<SalesOrderRespVO> convertList(List<SalesOrderDO> list);

    PageResult<SalesOrderRespVO> convertPage(PageResult<SalesOrderDO> page);

    List<SalesOrderExcelVO> convertList02(List<SalesOrderDO> list);

}
