package com.dofast.module.wms.controller.admin.issueline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产领料单行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IssueLineCreateReqVO extends IssueLineBaseVO {

}
