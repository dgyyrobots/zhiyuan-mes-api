package com.dofast.module.iot.controller.admin.devicelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备日志 Excel VO
 *
 * @author 惠智造
 */
@Data
public class DeviceLogExcelVO {

    @ExcelProperty("设备日志ID")
    private Long id;

    @ExcelProperty("标识符")
    private String identity;

    @ExcelProperty("类型（1=属性上报，2=调用功能，3=事件上报，4=设备升级，5=设备上线，6=设备离线）")
    private Boolean logType;

    @ExcelProperty("日志值")
    private String logValue;

    @ExcelProperty("设备ID")
    private Long deviceId;

    @ExcelProperty("设备名称")
    private String deviceName;

    @ExcelProperty("设备编号")
    private String serialNumber;

    @ExcelProperty("是否监测数据（1=是，0=否）")
    private Byte isMonitor;

    @ExcelProperty("模式(1=影子模式，2=在线模式，3=其他)")
    private Byte mode;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户昵称")
    private String userName;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
