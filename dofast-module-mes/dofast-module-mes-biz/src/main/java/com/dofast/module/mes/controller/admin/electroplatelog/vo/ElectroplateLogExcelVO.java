package com.dofast.module.mes.controller.admin.electroplatelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 制版房记录 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ElectroplateLogExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("设备ID")
    private Long machineryId;

    @ExcelProperty("设备编码")
    private String machineryCode;

    @ExcelProperty("设备名称")
    private String machineryName;

    @ExcelProperty("比例")
    private String proportion;

    @ExcelProperty("温度")
    private String temperature;

    @ExcelProperty("PH值")
    private String phValue;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
