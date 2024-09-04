package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 电子面单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressElectronicsheetCreateReqVO extends ExpressElectronicsheetBaseVO {

}
