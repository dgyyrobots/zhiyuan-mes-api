package com.dofast.module.iot.controller.admin.alert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备告警 Excel VO
 *
 * @author 惠智造
 */
@Data
public class AlertExcelVO {

    @ExcelProperty("告警ID")
    private Long id;

    @ExcelProperty("告警名称")
    private String alertName;

    @ExcelProperty("告警级别（1=提醒通知，2=轻微问题，3=严重警告）")
    private Byte alertLevel;

    @ExcelProperty("产品ID")
    private Long productId;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("触发器")
    private String triggers;

    @ExcelProperty("执行动作")
    private String actions;

    @ExcelProperty("告警状态（1-启动，2-停止）")
    private Boolean status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
