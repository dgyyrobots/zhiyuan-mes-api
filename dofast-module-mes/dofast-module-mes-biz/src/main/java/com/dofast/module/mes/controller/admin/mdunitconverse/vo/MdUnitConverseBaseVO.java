package com.dofast.module.mes.controller.admin.mdunitconverse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 单位换算 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdUnitConverseBaseVO {

    @Schema(description = "原编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "原编码不能为空")
    private String measureCode;

    @Schema(description = "原单位数量", example = "15969")
    private BigDecimal originCount;

    @Schema(description = "转换单位")
    private String converseCode;

    @Schema(description = "转换数量", example = "15766")
    private BigDecimal converseCount;

}
