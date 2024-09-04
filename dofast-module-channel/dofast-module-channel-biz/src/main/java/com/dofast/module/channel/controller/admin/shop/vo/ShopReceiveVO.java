package com.dofast.module.channel.controller.admin.shop.vo;

import com.dofast.module.channel.controller.admin.order.vo.OrderBaseVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;

@Schema(description = "管理后台 - 店铺创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopReceiveVO extends ShopBaseVO {
}
