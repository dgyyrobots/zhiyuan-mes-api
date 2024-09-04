package com.dofast.module.wms.controller.admin.transfer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 转移单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferCreateReqVO extends TransferBaseVO {
    @Schema(description = "转移单ID", example = "8558")
    private Long id;
}
