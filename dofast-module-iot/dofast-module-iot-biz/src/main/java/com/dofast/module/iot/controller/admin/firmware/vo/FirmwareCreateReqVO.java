package com.dofast.module.iot.controller.admin.firmware.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品固件创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FirmwareCreateReqVO extends FirmwareBaseVO {

}
