package com.dofast.module.mes.controller.admin.mdclient.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 客户创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdClientCreateReqVO extends MdClientBaseVO {
    @Schema(description = "客户ID", example = "6498")
    private Long id;
}
