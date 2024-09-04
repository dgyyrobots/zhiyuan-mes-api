package com.dofast.module.pro.controller.admin.transorder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 流转单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransOrderCreateReqVO extends TransOrderBaseVO {
    @Schema(description = "流转单ID", example = "17276")
    private Long id;
}
