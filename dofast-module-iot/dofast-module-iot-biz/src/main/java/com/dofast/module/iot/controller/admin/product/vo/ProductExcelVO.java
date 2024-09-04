package com.dofast.module.iot.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ProductExcelVO {

    @ExcelProperty("产品ID")
    private Long id;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("产品分类ID")
    private Long categoryId;

    @ExcelProperty("产品分类名称")
    private String categoryName;

    @ExcelProperty("是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @ExcelProperty("是否启用授权码（0-否，1-是）")
    private Boolean isAuthorize;

    @ExcelProperty("mqtt账号")
    private String mqttAccount;

    @ExcelProperty("mqtt密码")
    private String mqttPassword;

    @ExcelProperty("产品秘钥")
    private String mqttSecret;

    @ExcelProperty("状态（1-未发布，2-已发布）")
    private Boolean status;

    @ExcelProperty("物模型JSON（属性、功能、事件）")
    private String thingsModelsJson;

    @ExcelProperty("设备类型（1-直连设备、2-网关子设备、3-网关设备）")
    private Boolean deviceType;

    @ExcelProperty("联网方式（1=wifi、2=蜂窝(2G/3G/4G/5G)、3=以太网、4=其他）")
    private Boolean networkMethod;

    @ExcelProperty("认证方式（1-简单认证、2-加密认证、3-简单+加密）")
    private Boolean vertificateMethod;

    @ExcelProperty("图片地址")
    private String imgUrl;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
