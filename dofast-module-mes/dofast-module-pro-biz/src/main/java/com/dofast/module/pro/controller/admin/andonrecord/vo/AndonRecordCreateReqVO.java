package com.dofast.module.pro.controller.admin.andonrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 安灯呼叫记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AndonRecordCreateReqVO extends AndonRecordBaseVO {

}
