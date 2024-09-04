package com.dofast.module.qms.controller.admin.index.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测项创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IndexCreateReqVO extends IndexBaseVO {
    @Schema(description = "检测项ID", example = "20679")
    private Long id;
}
