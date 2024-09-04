package com.dofast.module.wms.controller.admin.rtvendor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 供应商退货 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RtVendorExcelVO {

    @ExcelProperty("退货单ID")
    private Long id;

    @ExcelProperty("退货单编号")
    private String rtCode;

    @ExcelProperty("退货单名称")
    private String rtName;

    @ExcelProperty("采购订单编号")
    private String poCode;

    @ExcelProperty("供应商ID")
    private Long vendorId;

    @ExcelProperty("供应商编码")
    private String vendorCode;

    @ExcelProperty("供应商名称")
    private String vendorName;

    @ExcelProperty("供应商简称")
    private String vendorNick;

    @ExcelProperty("批次号")
    private String batchCode;

    @ExcelProperty("退货日期")
    private LocalDateTime rtDate;

    @ExcelProperty("单据状态")
    private String status;

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
