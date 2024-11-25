package com.dofast.module.system.controller.admin.user.vo.usersFace;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户人脸数据 Excel 导出 Request VO，参数和 UsersFacePageReqVO 是一致的")
@Data
public class UsersFaceExportReqVO {

    @Schema(description = "用户ID", example = "30911")
    private Long userId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "人脸地址")
    private String faceImg;

}
