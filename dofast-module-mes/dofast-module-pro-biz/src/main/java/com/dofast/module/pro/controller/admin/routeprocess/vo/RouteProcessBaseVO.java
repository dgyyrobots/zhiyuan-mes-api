package com.dofast.module.pro.controller.admin.routeprocess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 工艺组成 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RouteProcessBaseVO {

    @Schema(description = "工艺路线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8819")
    @NotNull(message = "工艺路线ID不能为空")
    private Long routeId;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7829")
    @NotNull(message = "工序ID不能为空")
    private Long processId;

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "工序名称", example = "李四")
    private String processName;

    @Schema(description = "序号")
    private Integer orderNum;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28184")
    private Long nextProcessId;

    @Schema(description = "工序编码")
    private String nextProcessCode;

    @Schema(description = "工序名称", example = "芋艿")
    private String nextProcessName;

    @Schema(description = "与下一道工序关系", example = "1")
    private String linkType;

    @Schema(description = "准备时间")
    private Integer defaultPreTime;

    @Schema(description = "等待时间")
    private Integer defaultSufTime;

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

    public Long getId(){return null;}
}
