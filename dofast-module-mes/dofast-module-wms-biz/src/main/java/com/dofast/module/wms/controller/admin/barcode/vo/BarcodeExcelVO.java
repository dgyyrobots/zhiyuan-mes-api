package com.dofast.module.wms.controller.admin.barcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 条码清单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class BarcodeExcelVO {

    @ExcelProperty("条码ID")
    private Long id;

    @ExcelProperty("条码格式")
    private String barcodeFormart;

    @ExcelProperty("条码类型")
    private String barcodeType;

    @ExcelProperty("条码内容")
    private String barcodeContent;

    @ExcelProperty("业务ID")
    private Long bussinessId;

    @ExcelProperty("业务编码")
    private String bussinessCode;

    @ExcelProperty("业务名称")
    private String bussinessName;

    @ExcelProperty("条码地址")
    private String barcodeUrl;

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
