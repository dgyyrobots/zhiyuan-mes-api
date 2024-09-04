package com.dofast.module.pro.controller.admin.processcontent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 生产工序内容 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProcessContentBaseVO {

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20337")
    @NotNull(message = "工序ID不能为空")
    private Long processId;

    @Schema(description = "顺序编号")
    private Integer orderNum;

    @Schema(description = "内容说明")
    private String contentText;

    @Schema(description = "辅助设备")
    private String device;

    @Schema(description = "辅助材料")
    private String material;

    @Schema(description = "材料URL", example = "https://www.iocoder.cn")
    private String docUrl;

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

    public Long getId(){
        return null;
    }
}
