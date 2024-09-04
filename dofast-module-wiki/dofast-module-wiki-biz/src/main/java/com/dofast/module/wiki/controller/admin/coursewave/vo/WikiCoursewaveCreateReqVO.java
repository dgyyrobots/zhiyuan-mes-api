package com.dofast.module.wiki.controller.admin.coursewave.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 首页知识列表的信息	创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiCoursewaveCreateReqVO extends WikiCoursewaveBaseVO {

}
