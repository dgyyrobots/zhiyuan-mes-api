package com.dofast.module.mes.controller.admin.mdproductsop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品SOP Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdProductSopBaseVO {

    @Schema(description = "物料产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16008")
    @NotNull(message = "物料产品ID不能为空")
    private Long itemId;

    @Schema(description = "排列顺序")
    private Integer orderNum;

    @Schema(description = "对应的工序", example = "30499")
    private Long processId;

    @Schema(description = "工序编号")
    private String processCode;

    @Schema(description = "工序名称", example = "李四")
    private String processName;

    @Schema(description = "标题")
    private String sopTitle;

    @Schema(description = "详细描述", example = "你猜")
    private String sopDescription;

    @Schema(description = "图片地址", example = "https://www.iocoder.cn")
    private String sopUrl;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

}
