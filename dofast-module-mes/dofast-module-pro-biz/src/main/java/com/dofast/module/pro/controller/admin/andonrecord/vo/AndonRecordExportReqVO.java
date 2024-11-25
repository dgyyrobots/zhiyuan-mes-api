package com.dofast.module.pro.controller.admin.andonrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 安灯呼叫记录 Excel 导出 Request VO，参数和 AndonRecordPageReqVO 是一致的")
@Data
public class AndonRecordExportReqVO {

    @Schema(description = "工作站ID", example = "23978")
    private Long workstationId;

    @Schema(description = "工作站编号")
    private String workstationCode;

    @Schema(description = "工作站名称", example = "张三")
    private String workstationName;

    @Schema(description = "用户ID", example = "4949")
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

    @Schema(description = "呼叫原因", example = "不对")
    private String andonReason;

    @Schema(description = "级别")
    private String andonLevel;

    @Schema(description = "操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] operationTime;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updateBy;

}
