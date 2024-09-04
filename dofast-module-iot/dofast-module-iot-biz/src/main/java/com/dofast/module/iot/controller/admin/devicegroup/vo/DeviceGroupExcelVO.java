package com.dofast.module.iot.controller.admin.devicegroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备分组 Excel VO
 *
 * @author 惠智造
 */
@Data
public class DeviceGroupExcelVO {

    @ExcelProperty("设备ID")
    private Long id;

    @ExcelProperty("分组ID")
    private Long groupId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
