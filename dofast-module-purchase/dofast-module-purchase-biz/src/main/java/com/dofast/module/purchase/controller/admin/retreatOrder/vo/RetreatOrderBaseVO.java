package com.dofast.module.purchase.controller.admin.retreatOrder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * ERP仓退单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RetreatOrderBaseVO {

    @Schema(description = "仓退单编号")
    private String retreatCode;

    @Schema(description = "仓退单名称", example = "王五")
    private String retreatName;

    @Schema(description = "供应商名称", example = "王五")
    private String vendorName;

    @Schema(description = "供应商编号")
    private String vendorCode;

    @Schema(description = "申请人员")
    private String retreatUser;

    @Schema(description = "申请人员")
    private String retreatNick;

    @Schema(description = "仓退原因")
    private String retreatType;

    @Schema(description = "状态")
    private String status;



}
