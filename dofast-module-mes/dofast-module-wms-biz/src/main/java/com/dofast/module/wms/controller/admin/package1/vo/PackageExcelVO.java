package com.dofast.module.wms.controller.admin.package1.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 装箱单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class PackageExcelVO {

    @ExcelProperty("装箱单ID")
    private Long id;

    @ExcelProperty("父箱ID")
    private Long parentId;

    @ExcelProperty("所有父节点ID")
    private String ancestors;

    @ExcelProperty("装箱单编号")
    private String packageCode;

    @ExcelProperty("条码ID")
    private Long barcodeId;

    @ExcelProperty("条码内容")
    private String barcodeContent;

    @ExcelProperty("条码地址")
    private String barcodeUrl;

    @ExcelProperty("装箱日期")
    private LocalDateTime packageDate;

    @ExcelProperty("销售订单编号")
    private String soCode;

    @ExcelProperty("发票编号")
    private String invoiceCode;

    @ExcelProperty("客户ID")
    private Long clientId;

    @ExcelProperty("客户编码")
    private String clientCode;

    @ExcelProperty("客户名称")
    private String clientName;

    @ExcelProperty("客户简称")
    private String clientNick;

    @ExcelProperty("箱长度")
    private BigDecimal packageLength;

    @ExcelProperty("箱宽度")
    private BigDecimal packageWidth;

    @ExcelProperty("箱高度")
    private BigDecimal packageHeight;

    @ExcelProperty("尺寸单位")
    private String sizeUnit;

    @ExcelProperty("净重")
    private BigDecimal netWeight;

    @ExcelProperty("毛重")
    private BigDecimal crossWeight;

    @ExcelProperty("重量单位")
    private String weightUnit;

    @ExcelProperty("检查员用户名")
    private String inspector;

    @ExcelProperty("检查员名称")
    private String inspectorName;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("是否生效")
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

}
