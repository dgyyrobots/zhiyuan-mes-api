package com.dofast.module.system.controller.admin.form.vo;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 字段 Excel 导出 Request VO，参数和 FormItemPageReqVO 是一致的")
@Data
public class FormItemExportReqVO {

    @Schema(description = "名称", example = "芋艿")
    private String name;

    @Schema(description = "字段名")
    private String field;

    @Schema(description = "验证器")
    private String validator;

    @Schema(description = "主表单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主表单不能为空")
    private Long formId;

    @Schema(description = "类型", example = "2")
    private String type;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
