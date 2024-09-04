package com.dofast.module.cmms.controller.admin.dvrepairline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 设备维修单行 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DvRepairLineBaseVO {

    @Schema(description = "维修单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "693")
    @NotNull(message = "维修单ID不能为空")
    private Long repairId;

    @Schema(description = "项目ID", example = "21861")
    private Long subjectId;

    @Schema(description = "项目编码")
    private String subjectCode;

    @Schema(description = "项目名称", example = "王五")
    private String subjectName;

    @Schema(description = "项目类型", example = "1")
    private String subjectType;

    @Schema(description = "项目内容")
    private String subjectContent;

    @Schema(description = "标准")
    private String subjectStandard;

    @Schema(description = "故障描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "故障描述不能为空")
    private String malfunction;

    @Schema(description = "故障描述资源", example = "https://www.iocoder.cn")
    private String malfunctionUrl;

    @Schema(description = "维修情况")
    private String repairDes;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

}
