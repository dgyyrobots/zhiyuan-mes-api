package com.dofast.module.mes.controller.admin.mditemtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 物料产品分类 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdItemTypeBaseVO {

    @Schema(description = "产品物料类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料类型编码不能为空")
    private String itemTypeCode;

    @Schema(description = "产品物料类型名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "产品物料类型名称不能为空")
    private String itemTypeName;

    @Schema(description = "父类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23927")
    @NotNull(message = "父类型ID不能为空")
    private Long parentTypeId;

    @Schema(description = "所有层级父节点", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ancestors;

    @Schema(description = "产品物料标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料标识不能为空")
    private String itemOrProduct;

    @Schema(description = "排列顺序")
    private Integer orderNum;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
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

    public Long getId(){
        return null;
    }
}
