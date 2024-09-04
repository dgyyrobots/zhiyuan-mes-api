package com.dofast.module.wms.controller.admin.warehouse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 仓库 Excel VO
 *
 * @author 惠智造
 */
@Data
public class WarehouseExcelVO {

    @ExcelProperty("仓库ID")
    private Long id;

    @ExcelProperty("仓库编码")
    private String warehouseCode;

    @ExcelProperty("仓库名称")
    private String warehouseName;

    @ExcelProperty("位置")
    private String location;

    @ExcelProperty("面积")
    private BigDecimal area;

    @ExcelProperty("负责人")
    private String charge;

    @ExcelProperty("是否启用")
    private String enableFlag;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("预留字段1")
    private String attr1;

    @ExcelProperty("预留字段2")
    private String attr2;

    @ExcelProperty("预留字段3")
    private Integer attr3;

    @ExcelProperty("预留字段4")
    private Integer attr4;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("寄件人省")
    private String sendState;

    @ExcelProperty("寄件人市")
    private String sendCity;

    @ExcelProperty("寄件人区/县")
    private String sendDistrict;

    @ExcelProperty("寄件人镇")
    private String sendStreet;

    @ExcelProperty("寄件人详细地址")
    private String sendDetail;

    @ExcelProperty("寄件人姓名")
    private String sendName;

    @ExcelProperty("寄件人电话号码")
    private String sendPhone;

    @ExcelProperty("寄件人手机号码")
    private String sendMobile;

}
