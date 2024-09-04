package com.dofast.module.channel.controller.admin.ordergoods.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * 子订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class OrderGoodsExcelVO {

    @ExcelProperty("订单商品")
    private Integer id;

    @ExcelProperty("关联的id")
    private String refOid;

    @ExcelProperty("子订单id")
    private String refOlId;

    @ExcelProperty("平台SPU_ID")
    private String refSpuId;

    @ExcelProperty("平台SKU_ID")
    private String refSkuId;

    @ExcelProperty("商家编码")
    private String outerId;

    @ExcelProperty("货品标题")
    private String title;

    @ExcelProperty("货品规格")
    private String standards;

    @ExcelProperty("售价合计")
    private BigDecimal totalSellPrice;

    @ExcelProperty("原价合计")
    private BigDecimal totalPrice;

    @ExcelProperty("原价")
    private BigDecimal price;

    @ExcelProperty("售价")
    private BigDecimal sellPrice;

    @ExcelProperty("应收金额")
    private BigDecimal totalFee;

    @ExcelProperty("单个商品应收金额")
    private BigDecimal singleFee;

    @ExcelProperty("数量")
    private Integer num;

    @ExcelProperty(value = "子订单售后状态", converter = DictConvert.class)
    @DictFormat("dian3_sub_refund_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String refundStatus;

    @ExcelProperty(value = "子订单状态", converter = DictConvert.class)
    @DictFormat("dian3_order_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String status;

    @ExcelProperty("商品图片URL")
    private String picUrl;

    @ExcelProperty(value = "是否赠品", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer isFreeGift;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("关联用户ID")
    private Integer userId;

}
