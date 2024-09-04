package com.dofast.module.wms.controller.admin.transfer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 转移单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class TransferExcelVO {

    @ExcelProperty("转移单ID")
    private Long id;

    @ExcelProperty("转移单编号")
    private String transferCode;

    @ExcelProperty("转移单名称")
    private String transferName;

    @ExcelProperty("转移单类型")
    private String transferType;

    @ExcelProperty("目的地")
    private String destination;

    @ExcelProperty("承运商")
    private String carrier;

    @ExcelProperty("托运单号")
    private String bookingNote;

    @ExcelProperty("收货人")
    private String receiver;

    @ExcelProperty("收货人名称")
    private String receiverNick;

    @ExcelProperty("移出仓库ID")
    private Long fromWarehouseId;

    @ExcelProperty("移出仓库编码")
    private String fromWarehouseCode;

    @ExcelProperty("移出仓库名称")
    private String fromWarehouseName;

    @ExcelProperty("移入仓库ID")
    private Long toWarehouseId;

    @ExcelProperty("移入仓库编码")
    private String toWarehouseCode;

    @ExcelProperty("移入仓库名称")
    private String toWarehouseName;

    @ExcelProperty("转移日期")
    private LocalDateTime transferDate;

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
