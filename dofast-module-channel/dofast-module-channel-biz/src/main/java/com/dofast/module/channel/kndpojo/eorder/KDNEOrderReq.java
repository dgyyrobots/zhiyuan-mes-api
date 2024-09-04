package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;

@Schema(description = "快递鸟 电子面单 Request VO")
@ToString(callSuper = true)
@Data
public class KDNEOrderReq {
    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ShipperCode;

    @Schema(description = "电子面单账号用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String CustomerName;

    @Schema(description = "电子面单账号密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String CustomerPwd;

    @Schema(description = "电子面单账号网点", requiredMode = Schema.RequiredMode.REQUIRED)
    private String SendSite;

    @Schema(description = "电子面单账号发送员工", requiredMode = Schema.RequiredMode.REQUIRED)
    private String SendStaff;

    @Schema(description = "月结号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String MonthCode;

    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String OrderCode;

    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Integer PayType;

    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ExpType;

    @Schema(description = "收件人信息",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private KDNEOrderReciver Receiver;

    @Schema(description = "寄件人信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private KDNEOrderSender Sender;

    @Schema(description = "包裹数", requiredMode = Schema.RequiredMode.REQUIRED)
    @Max(50)
    @Min(1)
    private Integer Quantity;

    @Schema(description = "包裹总重量kg,京东必填,快运类必填", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double Weight;

    @Schema(description = "包裹总体积m^3,京东必填,快运类必填", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double Volume;

    @Schema(description = "快递运费,信丰物流", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double Cost;

    @Schema(description = "其它费用", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double OtherCost;

    @Schema(description = "增值服务信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private KDNEOrderAddService AddService;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Remark;

    @Schema(description = "商品信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Collection<KDNEOrderCommodity> Commodity;

    @Schema(description = "是否返回电子面单模板：（不填默认为0）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String IsReturnPrintTemplate;

    @Schema(description = "模板的规格， 默认模板无需传值,非默认传尺寸", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String TemplateSize;

    @Schema(description = "商家自定义区域", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String CustomArea;

    @Schema(description = "配送产品类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String TransType;

    @Schema(description = "包装类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer PackingType;

    @Schema(description = "送货方式/派送类型/配送方式  安能必填", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer DeliveryMethod;

    @Schema(description = "是否订阅轨迹推送", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String IsSubscribe = "1";

    @Schema(description = "自定义回传字段", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Callback;

    @Schema(description = "发货仓编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String WareHouseID;

    @Schema(description = "第三方平台订单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String TheOrderCode;

    @Schema(description = "是否通知快递员上门揽件", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer IsNotice;

    @Schema(description = "上门揽件开始时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String StartDate;

    @Schema(description = "上门揽件结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String EndDate;

    @Schema(description = "发货仓编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String MemberID;

    @Schema(description = "发货仓编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String IsReturnSignBill;

    @Schema(description = "发货仓编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String OperateRequire;

    @Schema(description = "发货仓编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String IsSendMessage;

    @Schema(description = "发货仓编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String CurrencyCode;

    @Schema(description = "申报价值", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal DeclaredValue;

    @Schema(description = "快递单号(仅宅急送可用)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String LogisticCode;
}
