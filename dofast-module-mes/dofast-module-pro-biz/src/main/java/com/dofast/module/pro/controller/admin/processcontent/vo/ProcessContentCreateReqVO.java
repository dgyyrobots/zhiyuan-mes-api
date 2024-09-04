package com.dofast.module.pro.controller.admin.processcontent.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工序内容创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessContentCreateReqVO extends ProcessContentBaseVO {
    @Schema(description = "内容ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2138")
    private Long id;
}
