package com.dofast.module.trade.convert.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 采集任务类型 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface OrderCollectionTypeConvert {

    OrderCollectionTypeConvert INSTANCE = Mappers.getMapper(OrderCollectionTypeConvert.class);

    OrderCollectionTypeDO convert(OrderCollectionTypeCreateReqVO bean);

    OrderCollectionTypeDO convert(OrderCollectionTypeUpdateReqVO bean);

    OrderCollectionTypeRespVO convert(OrderCollectionTypeDO bean);

    List<OrderCollectionTypeRespVO> convertList(List<OrderCollectionTypeDO> list);

    PageResult<OrderCollectionTypeRespVO> convertPage(PageResult<OrderCollectionTypeDO> page);

    List<OrderCollectionTypeExcelVO> convertList02(List<OrderCollectionTypeDO> list);

    List<OrderCollectionTypeSimpleRespVO> convertList03(List<OrderCollectionTypeDO> list);

}
