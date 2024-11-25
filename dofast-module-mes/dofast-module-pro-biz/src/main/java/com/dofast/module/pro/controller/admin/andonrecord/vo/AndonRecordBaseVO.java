package com.dofast.module.pro.controller.admin.andonrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 安灯呼叫记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AndonRecordBaseVO {

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23978")
    @NotNull(message = "工作站ID不能为空")
    private Long workstationId;

    @Schema(description = "工作站编号")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "张三")
    private String workstationName;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4949")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "用户名", example = "王五")
    private String userName;

    @Schema(description = "名称", example = "芋艿")
    private String nickName;

    @Schema(description = "生产工单ID", example = "21127")
    private Long workorderId;

    @Schema(description = "生产工单编号")
    private String workorderCode;

    @Schema(description = "生产工单名称", example = "王五")
    private String workorderName;

    @Schema(description = "工序ID", example = "1311")
    private Long processId;

    @Schema(description = "工序编号")
    private String processCode;

    @Schema(description = "工序名称", example = "王五")
    private String processName;

    @Schema(description = "呼叫原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不对")
    @NotNull(message = "呼叫原因不能为空")
    private String andonReason;

    @Schema(description = "级别")
    private String andonLevel;

    @Schema(description = "操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime operationTime;

    @Schema(description = "激活中", example = "2")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

}
