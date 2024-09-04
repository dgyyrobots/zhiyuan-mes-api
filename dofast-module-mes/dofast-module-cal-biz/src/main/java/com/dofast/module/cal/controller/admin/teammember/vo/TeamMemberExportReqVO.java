package com.dofast.module.cal.controller.admin.teammember.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 班组成员 Excel 导出 Request VO，参数和 TeamMemberPageReqVO 是一致的")
@Data
public class TeamMemberExportReqVO {

    @Schema(description = "班组ID", example = "21314")
    private Long teamId;

    @Schema(description = "用户ID", example = "14114")
    private Long userId;

    @Schema(description = "用户名", example = "李四")
    private String userName;

    @Schema(description = "用户昵称", example = "赵六")
    private String nickName;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
