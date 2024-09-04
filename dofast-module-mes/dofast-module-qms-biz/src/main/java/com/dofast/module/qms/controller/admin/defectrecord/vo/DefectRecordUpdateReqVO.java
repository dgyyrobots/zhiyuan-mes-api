package com.dofast.module.qms.controller.admin.defectrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检验单缺陷记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DefectRecordUpdateReqVO extends DefectRecordBaseVO {

    @Schema(description = "缺陷ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7251")
//    @NotNull(message = "缺陷ID不能为空")
    private Long id;

}
