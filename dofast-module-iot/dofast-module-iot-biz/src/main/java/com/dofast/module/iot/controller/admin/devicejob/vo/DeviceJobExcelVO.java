package com.dofast.module.iot.controller.admin.devicejob.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备定时 Excel VO
 *
 * @author 惠智造
 */
@Data
public class DeviceJobExcelVO {

    @ExcelProperty("任务ID")
    private Long id;

    @ExcelProperty("任务名称")
    private String jobName;

    @ExcelProperty("任务组名")
    private String jobGroup;

    @ExcelProperty("cron执行表达式")
    private String cronExpression;

    @ExcelProperty("计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    private String misfirePolicy;

    @ExcelProperty("是否并发执行（0允许 1禁止）")
    private String concurrent;

    @ExcelProperty("状态（0正常 1暂停）")
    private String status;

    @ExcelProperty("备注信息")
    private String remark;

    @ExcelProperty("设备ID")
    private Long deviceId;

    @ExcelProperty("设备编号")
    private String serialNumber;

    @ExcelProperty("设备名称")
    private String deviceName;

    @ExcelProperty("是否详细corn表达式（1=是，0=否）")
    private Boolean isAdvance;

    @ExcelProperty("执行的动作集合")
    private String actions;

    @ExcelProperty("任务类型（1=设备定时，2=设备告警，3=场景联动）")
    private Boolean jobType;

    @ExcelProperty("产品ID")
    private Long productId;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("场景联动ID")
    private Long sceneId;

    @ExcelProperty("告警ID")
    private Long alertId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
