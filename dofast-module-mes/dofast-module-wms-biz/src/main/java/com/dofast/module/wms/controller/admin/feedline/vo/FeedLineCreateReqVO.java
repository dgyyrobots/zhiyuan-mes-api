package com.dofast.module.wms.controller.admin.feedline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 上料详情创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedLineCreateReqVO extends FeedLineBaseVO {

}
