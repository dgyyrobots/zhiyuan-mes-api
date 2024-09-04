package com.dofast.module.channel.dian3logisticspojo.logistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@Schema(description = "快递鸟物流模块 轨迹查询 商家物流鉴权信息 Response VO")
@ToString(callSuper = true)
public class Dian3LogisticsResAuthInfo {
    @Schema(description = "店铺鉴权类型字段： 店铺平台用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refUserId;

    @Schema(description = "店铺鉴权类型字段： 店铺平台用户昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refUserNick;

    @Schema(description = "店铺-网点类型字段： 绑定网点编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String branchCode;

    @Schema(description = "店铺-网点类型字段： 绑定网点名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String branchName;

    @Schema(description = "顺丰丰桥特有字段： 合作伙伴编码（即顾客编码）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sfPartnerID;

    @Schema(description = "店铺-网点类型字段： 绑定网点地址信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String branchAddress;
}
