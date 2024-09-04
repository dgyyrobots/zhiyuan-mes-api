package com.dofast.module.mes.controller.admin.autocoderesult.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 编码生成记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodeResultPageReqVO extends PageParam {

    @Schema(description = "规则ID", example = "6039")
    private Long ruleId;

    @Schema(description = "生成日期时间")
    private String genDate;

    @Schema(description = "最后产生的序号")
    private Integer genIndex;

    @Schema(description = "最后产生的值")
    private String lastResult;

    @Schema(description = "最后产生的流水号")
    private Integer lastSerialNo;

    @Schema(description = "最后传入的参数")
    private String lastInputChar;

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

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

}
