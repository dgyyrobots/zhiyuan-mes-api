package com.dofast.module.wms.controller.admin.electroformheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 制版房领料单头 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ElectroformHeaderExcelVO {

    @ExcelProperty("制版领料单ID")
    private Long id;

    @ExcelProperty("制版领料单编号")
    private String issueCode;

    @ExcelProperty("制版领料单名称")
    private String issueName;

    @ExcelProperty("领料日期")
    private LocalDateTime issueDate;

    @ExcelProperty("单据状态")
    private String status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("生产设备名称")
    private String machineryName;

    @ExcelProperty("生产设备编码")
    private String machineryCode;

    @ExcelProperty("生产设备ID")
    private Long machineryId;

}
