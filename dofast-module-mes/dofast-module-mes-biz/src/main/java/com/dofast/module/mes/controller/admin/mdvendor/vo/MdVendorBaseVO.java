package com.dofast.module.mes.controller.admin.mdvendor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 供应商 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdVendorBaseVO {

    @Schema(description = "供应商编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "供应商编码不能为空")
    private String vendorCode;

    @Schema(description = "供应商名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "供应商名称不能为空")
    private String vendorName;

    @Schema(description = "供应商简称")
    private String vendorNick;

    @Schema(description = "供应商英文名称")
    private String vendorEn;

    @Schema(description = "供应商简介")
    private String vendorDes;

    @Schema(description = "供应商LOGO地址")
    private String vendorLogo;

    @Schema(description = "供应商等级")
    private String vendorLevel;

    @Schema(description = "供应商评分")
    private Integer vendorScore;

    @Schema(description = "供应商地址")
    private String address;

    @Schema(description = "供应商官网地址")
    private String website;

    @Schema(description = "供应商邮箱地址")
    private String email;

    @Schema(description = "供应商电话")
    private String tel;

    @Schema(description = "联系人1")
    private String contact1;

    @Schema(description = "联系人1-电话")
    private String contact1Tel;

    @Schema(description = "联系人1-邮箱")
    private String contact1Email;

    @Schema(description = "联系人2")
    private String contact2;

    @Schema(description = "联系人2-电话")
    private String contact2Tel;

    @Schema(description = "联系人2-邮箱")
    private String contact2Email;

    @Schema(description = "统一社会信用代码")
    private String creditCode;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
    private String enableFlag;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    public Long getId(){
        return null;
    }
}
