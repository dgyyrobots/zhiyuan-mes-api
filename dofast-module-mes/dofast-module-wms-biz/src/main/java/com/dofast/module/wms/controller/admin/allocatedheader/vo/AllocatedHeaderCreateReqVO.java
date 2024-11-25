package com.dofast.module.wms.controller.admin.allocatedheader.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 调拨单头创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class AllocatedHeaderCreateReqVO extends AllocatedHeaderBaseVO {
    List<Map<String, Object>> bomList;
}
