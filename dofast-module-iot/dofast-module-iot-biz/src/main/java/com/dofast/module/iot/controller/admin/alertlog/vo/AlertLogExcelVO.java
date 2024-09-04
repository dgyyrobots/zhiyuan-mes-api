package com.dofast.module.iot.controller.admin.alertlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备告警日志 Excel VO
 *
 * @author 惠智造
 */
@Data
public class AlertLogExcelVO {

    @ExcelProperty("告警ID")
    private Long id;

    @ExcelProperty("告警名称")
    private String alertName;

    @ExcelProperty("告警级别（1=提醒通知，2=轻微问题，3=严重警告）")
    private Byte alertLevel;

    @ExcelProperty("处理状态(1=不需要处理,2=未处理,3=已处理)")
    private Byte status;

    @ExcelProperty("产品ID")
    private Long productId;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("设备ID")
    private Long deviceId;

    @ExcelProperty("设备名称")
    private String deviceName;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户昵称")
    private String userName;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("类型（1=告警，2=场景联动）")
    private Byte type;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
