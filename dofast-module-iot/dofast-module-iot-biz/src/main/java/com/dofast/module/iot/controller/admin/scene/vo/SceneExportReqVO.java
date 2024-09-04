package com.dofast.module.iot.controller.admin.scene.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场景联动 Excel 导出 Request VO，参数和 ScenePageReqVO 是一致的")
@Data
public class SceneExportReqVO {

    @Schema(description = "场景名称", example = "芋艿")
    private String sceneName;

    @Schema(description = "用户ID", example = "31230")
    private Long userId;

    @Schema(description = "用户名称", example = "芋艿")
    private String userName;

    @Schema(description = "触发器")
    private String triggers;

    @Schema(description = "执行动作")
    private String actions;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
