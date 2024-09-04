package com.dofast.module.wms.controller.admin.barcodeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 条码配置 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class BarcodeConfigExcelVO {

    @ExcelProperty("配置ID")
    private Long id;

    @ExcelProperty("条码格式")
    private String barcodeFormart;

    @ExcelProperty("条码类型")
    private String barcodeType;

    @ExcelProperty("内容格式")
    private String contentFormart;

    @ExcelProperty("内容样例")
    private String contentExample;

    @ExcelProperty("是否自动生成")
    private String autoGenFlag;

    @ExcelProperty("默认的打印模板")
    private String defaultTemplate;

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
