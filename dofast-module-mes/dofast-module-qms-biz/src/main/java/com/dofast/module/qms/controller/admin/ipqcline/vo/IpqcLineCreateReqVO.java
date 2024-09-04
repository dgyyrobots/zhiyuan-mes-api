package com.dofast.module.qms.controller.admin.ipqcline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 过程检验单行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IpqcLineCreateReqVO extends IpqcLineBaseVO {

}
