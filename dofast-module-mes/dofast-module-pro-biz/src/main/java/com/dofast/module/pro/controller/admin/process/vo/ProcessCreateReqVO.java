package com.dofast.module.pro.controller.admin.process.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工序创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessCreateReqVO extends ProcessBaseVO {
    @Schema(description = "工序ID",example = "16302")
    private Long id;
}
