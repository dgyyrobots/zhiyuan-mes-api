package com.dofast.module.channel.controller.admin.address.vo;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 交易客户 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class AddressExcelVO {

    @ExcelProperty("订单ID")
    private Integer id;

    @ExcelProperty("相关单ID")
    private String refOid;

    @ExcelProperty("店铺编码")
    private String posCode;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty(value = "渠道", converter = DictConvert.class)
    @DictFormat("order_from_channel") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String refType;

    @ExcelProperty("收货人国家")
    private String receiverCountry;

    @ExcelProperty("收货人省")
    private String receiverState;

    @ExcelProperty("收货人市")
    private String receiverCity;

    @ExcelProperty("收件人区/县")
    private String receiverDistrict;

    @ExcelProperty("收货人镇")
    private String receiverTown;

    @ExcelProperty("收货人ID字段，可用于区分多个订单是否属于同一个收货人")
    private String receiverId;

    @ExcelProperty("买家昵称")
    private String openBuyerNick;

    @ExcelProperty("买家昵称")
    private String openBuyerId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("关联收货地址ID")
    private Integer addressId;

    @ExcelProperty("关联用户ID")
    private Integer userId;

}
