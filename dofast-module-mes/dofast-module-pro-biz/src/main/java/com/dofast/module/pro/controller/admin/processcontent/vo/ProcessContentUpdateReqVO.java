package com.dofast.module.pro.controller.admin.processcontent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工序内容更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessContentUpdateReqVO extends ProcessContentBaseVO {

    @Schema(description = "内容ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2138")
    @NotNull(message = "内容ID不能为空")
    private Long id;

}
