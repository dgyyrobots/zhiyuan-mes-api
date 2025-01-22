package com.dofast.module.wms.controller.admin.allocatedrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 调拨单身记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AllocatedRecordCreateReqVO extends AllocatedRecordBaseVO {

}
