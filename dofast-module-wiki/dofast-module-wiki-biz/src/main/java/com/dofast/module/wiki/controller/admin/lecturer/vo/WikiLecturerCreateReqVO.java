package com.dofast.module.wiki.controller.admin.lecturer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 讲师的信息	创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiLecturerCreateReqVO extends WikiLecturerBaseVO {

}
