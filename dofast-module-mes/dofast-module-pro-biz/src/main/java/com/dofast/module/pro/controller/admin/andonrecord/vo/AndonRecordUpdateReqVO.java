package com.dofast.module.pro.controller.admin.andonrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 安灯呼叫记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AndonRecordUpdateReqVO extends AndonRecordBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8063")
    @NotNull(message = "记录ID不能为空")
    private Long recordId;

}
