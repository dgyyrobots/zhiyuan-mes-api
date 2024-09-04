package com.dofast.module.channel.convert.order;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.order.vo.*;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 主订单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderDO convert(OrderCreateReqVO bean);

    OrderDO convert(OrderUpdateReqVO bean);

    OrderRespVO convert(OrderDO bean);

    List<OrderRespVO> convertList(List<OrderDO> list);

    PageResult<OrderGoodsVO> convertPage(PageResult<OrderGoodsVO> page);

    List<OrderExcelVO> convertList02(List<OrderDO> list);

    OrdedrNumberVO covert(OrdedrNumberVO bean);

    List<OrderShopDayNVO> convertList03(List<OrderShopDayNVO> list);

    public static <T, K> Map<K, List<T>> convertMultiMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(t -> t, Collectors.toList())));
    }
}
