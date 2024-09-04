package com.dofast.module.trade.convert.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionDO;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 采集任务 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface OrderCollectionConvert {

    OrderCollectionConvert INSTANCE = Mappers.getMapper(OrderCollectionConvert.class);

    OrderCollectionDO convert(OrderCollectionCreateReqVO bean);

    OrderCollectionDO convert(OrderCollectionUpdateReqVO bean);

    OrderCollectionRespVO convert(OrderCollectionDO bean);

    OrderCollectionUpdateReqVO convert(OrderCollectionUpdateStatusReqVO bean);

    List<OrderCollectionRespVO> convertList(List<OrderCollectionDO> list);

    PageResult<OrderCollectionRespVO> convertPage(PageResult<OrderCollectionDO> page);

    List<OrderCollectionExcelVO> convertList02(List<OrderCollectionDO> list);


    default PageResult<OrderCollectionRespVO> convertPage(PageResult<OrderCollectionDO> page, List<OrderCollectionTypeDO> types, List<TradeOrderDO> orders) {
        PageResult<OrderCollectionRespVO> pageResult = new PageResult<>();
        pageResult.setList(convertList(page.getList(), types, orders, false));
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }


    default List<OrderCollectionRespVO> convertList(List<OrderCollectionDO> list, List<OrderCollectionTypeDO> types, List<TradeOrderDO> orders, Boolean fullable) {
        fullable = fullable== null ? false:fullable;
        List<OrderCollectionRespVO> resultList = convertList(list);
        return CollectionUtils.convertList(resultList, collection -> {
            OrderCollectionTypeDO type = types.stream().filter((ot) -> ot.getId() == collection.getTypeId() ).findFirst().get();
            if (type != null) {
                collection.setFormId(type.getFormId());
                collection.setTypeName(type.getName());
            }
//            TradeOrderDO order = orders.stream().filter((o) -> o.getId() == collection.getOrderId() ).findFirst().get();

            TradeOrderDO order = orders.stream().filter((o) -> o.getId() == collection.getOrderId() ).findFirst().orElse(null);
            if (order != null) {
                collection.setOrderNo(order.getNo());
            }
            return collection;
        });
    }

}
