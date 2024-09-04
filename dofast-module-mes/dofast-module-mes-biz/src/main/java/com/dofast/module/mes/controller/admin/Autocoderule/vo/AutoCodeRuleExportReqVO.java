package com.dofast.module.mes.controller.admin.Autocoderule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 编码生成规则 Excel 导出 Request VO，参数和 AutoCodeRulePageReqVO 是一致的")
@Data
public class AutoCodeRuleExportReqVO {

    @Schema(description = "规则编码")
    private String ruleCode;

    @Schema(description = "规则名称", example = "李四")
    private String ruleName;

    @Schema(description = "描述")
    private String ruleDesc;

    @Schema(description = "最大长度")
    private Integer maxLength;

    @Schema(description = "是否补齐")
    private String isPadded;

    @Schema(description = "补齐字符")
    private String paddedChar;

    @Schema(description = "补齐方式")
    private String paddedMethod;

    @Schema(description = "是否启用")
    private String enableFlag;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
