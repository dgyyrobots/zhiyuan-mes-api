package com.dofast.module.wms.controller.admin.itemconsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料消耗记录 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ItemConsumeExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("生产工单ID")
    private Long workorderId;

    @ExcelProperty("生产工单编码")
    private String workorderCode;

    @ExcelProperty("生产工单名称")
    private String workorderName;

    @ExcelProperty("生产任务ID")
    private Long taskId;

    @ExcelProperty("生产任务编号")
    private String taskCode;

    @ExcelProperty("生产任务名称")
    private String taskName;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("工作站编号")
    private String workstationCode;

    @ExcelProperty("工作站名称")
    private String workstationName;

    @ExcelProperty("工序ID")
    private Long processId;

    @ExcelProperty("工序编号")
    private String processCode;

    @ExcelProperty("工序名称")
    private String processName;

    @ExcelProperty("消耗日期")
    private LocalDateTime consumeDate;

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
