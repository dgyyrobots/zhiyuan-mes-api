package com.dofast.module.pro.gantt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GanttData {
    /**
     * id
     */
    private String id;

    /**
     * TASK 类型：project；task
     */
    private String type;

    /**
     * 任务名称
     */
    private String text;

    /**
     * 工作站名称
     */
    private String workstation;

    /**
     * 生产的产品
     */
    private String product;

    /**
     * 排产数量
     */
    private BigDecimal quantity;

    /**
     * 生产进度
     */
    private float progress;

    /**
     * TASK的颜色
     */
    private String color;

    /**
     * 工序
     */
    private String process;


    /**
     * 父TASK ID
     */
    private String parent;


    /** 开始生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_date;

    /** 生产时长 */
    private Long duration;

    /** 完成生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end_date;

    /**
     * 任务编号，生产工单编号
     */
    private String code;
}
