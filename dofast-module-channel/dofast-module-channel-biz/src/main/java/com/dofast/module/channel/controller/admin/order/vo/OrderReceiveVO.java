package com.dofast.module.channel.controller.admin.order.vo;

import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 主订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderReceiveVO extends OrderBaseVO {
    ArrayList<OrderGoodsBaseVO> lines;
}
