package com.dofast.module.pro.controller.admin.andonrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 安灯呼叫记录 Excel VO
 *
 * @author 惠智造
 */
@Data
public class AndonRecordExcelVO {

    @ExcelProperty("记录ID")
    private Long recordId;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("工作站编号")
    private String workstationCode;

    @ExcelProperty("工作站名称")
    private String workstationName;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名")
    private String userName;

    @ExcelProperty("名称")
    private String nickName;

    @ExcelProperty("生产工单ID")
    private Long workorderId;

    @ExcelProperty("生产工单编号")
    private String workorderCode;

    @ExcelProperty("生产工单名称")
    private String workorderName;

    @ExcelProperty("工序ID")
    private Long processId;

    @ExcelProperty("工序编号")
    private String processCode;

    @ExcelProperty("工序名称")
    private String processName;

    @ExcelProperty("呼叫原因")
    private String andonReason;

    @ExcelProperty("级别")
    private String andonLevel;

    @ExcelProperty("操作时间")
    private LocalDateTime operationTime;

    @ExcelProperty("激活中")
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

    @ExcelProperty("创建者")
    private String createBy;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    private String updateBy;

}
