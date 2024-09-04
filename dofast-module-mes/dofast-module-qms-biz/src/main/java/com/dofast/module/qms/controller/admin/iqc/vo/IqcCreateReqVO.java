package com.dofast.module.qms.controller.admin.iqc.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 来料检验单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IqcCreateReqVO extends IqcBaseVO {
    @Schema(description = "来料检验单ID", example = "20687")
    private Long id;

}
