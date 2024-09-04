package com.dofast.module.channel.controller.admin.order.vo;

import com.dofast.framework.common.pojo.PageParam;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Schema(description = "管理后台 - 主订单详情 OrderGoods VO")
public class OrderDOGoodsVO extends OrderDO{
    @Schema(description = "存放订单商品的列表")
    private List<OrderGoodsDO> orderGoodsDOList;
}
