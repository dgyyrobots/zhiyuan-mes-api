package com.dofast.module.pro.controller.admin.feedbackmember.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 报工班组人员分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackMemberPageReqVO extends PageParam {

    @Schema(description = "报工单编号")
    private String feedbackId;

    @Schema(description = "生产任务编号")
    private String taskCode;

    @Schema(description = "班组编号")
    private String teamCode;

    @Schema(description = "用户ID", example = "20173")
    private Long userId;

    @Schema(description = "用户名", example = "芋艿")
    private String userName;

    @Schema(description = "用户昵称", example = "赵六")
    private String nickName;

    @Schema(description = "岗位Id")
    private String postIds;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
