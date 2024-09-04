package com.dofast.module.iot.controller.admin.group.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分组 Excel 导出 Request VO，参数和 GroupPageReqVO 是一致的")
@Data
public class GroupExportReqVO {

    @Schema(description = "分组名称", example = "赵六")
    private String groupName;

    @Schema(description = "分组排序")
    private Byte groupOrder;

    @Schema(description = "用户ID", example = "31061")
    private Long userId;

    @Schema(description = "用户昵称", example = "赵六")
    private String userName;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
