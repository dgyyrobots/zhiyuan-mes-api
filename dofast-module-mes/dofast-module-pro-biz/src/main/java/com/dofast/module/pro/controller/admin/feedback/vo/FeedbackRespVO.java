package com.dofast.module.pro.controller.admin.feedback.vo;

import com.dofast.module.pro.dal.dataobject.feedbackdefect.FeedbackDefectDO;
import com.dofast.module.pro.dal.dataobject.feedbackmember.FeedbackMemberDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 生产报工记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackRespVO extends FeedbackBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9900")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "报工班组人员")
    private List<FeedbackMemberDO> memberList;

    @Schema(description = "报工缺陷项列表")
    private List<FeedbackDefectDO> processDefectList;

}
