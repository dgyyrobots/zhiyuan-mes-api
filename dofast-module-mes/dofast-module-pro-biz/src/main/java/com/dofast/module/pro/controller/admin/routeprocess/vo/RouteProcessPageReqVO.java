package com.dofast.module.pro.controller.admin.routeprocess.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工艺组成分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteProcessPageReqVO extends PageParam {

    @Schema(description = "工艺路线ID", example = "8819")
    private Long routeId;

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "工序名称", example = "李四")
    private String processName;

    @Schema(description = "序号")
    private Integer orderNum;

    @Schema(description = "工序编码")
    private String nextProcessCode;

    @Schema(description = "工序名称", example = "芋艿")
    private String nextProcessName;

    @Schema(description = "与下一道工序关系", example = "1")
    private String linkType;

    @Schema(description = "准备时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] defaultPreTime;

    @Schema(description = "等待时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] defaultSufTime;

    @Schema(description = "甘特图显示颜色")
    private String colorCode;

    @Schema(description = "关键工序")
    private String keyFlag;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "工序ID", example = "29944")
    private Long processId;

    @Schema(description = "工序ID", example = "13497")
    private Long nextProcessId;

    @Schema(description = "项次")
    private Long sequence;


    @Schema(description = "工作序")
    private Long workorderSequence;

}
