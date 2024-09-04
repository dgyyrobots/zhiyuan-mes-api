package com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 物模型模板 Excel 导出 Request VO，参数和 ThingsModelTemplatePageReqVO 是一致的")
@Data
public class ThingsModelTemplateExportReqVO {

    @Schema(description = "物模型名称", example = "芋艿")
    private String templateName;

    @Schema(description = "标识符，产品下唯一")
    private String identifier;

    @Schema(description = "模型类别（1-属性，2-功能，3-事件）", example = "2")
    private Boolean type;

    @Schema(description = "数据类型（integer、decimal、string、bool、array、enum）", example = "2")
    private String datatype;

    @Schema(description = "数据定义")
    private String specs;

    @Schema(description = "是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @Schema(description = "是否首页显示（0-否，1-是）")
    private Boolean isTop;

    @Schema(description = "是否实时监测（0-否，1-是）")
    private Boolean isMonitor;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
