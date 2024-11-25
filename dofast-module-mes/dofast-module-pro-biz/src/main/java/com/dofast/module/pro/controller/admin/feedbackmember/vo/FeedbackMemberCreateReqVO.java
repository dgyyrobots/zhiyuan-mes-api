package com.dofast.module.pro.controller.admin.feedbackmember.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 报工班组人员创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackMemberCreateReqVO extends FeedbackMemberBaseVO {

}
