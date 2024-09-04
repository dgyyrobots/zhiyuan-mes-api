package com.dofast.module.wms.controller.admin.storagelocation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 库区 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class StorageLocationBaseVO {

    @Schema(description = "库区编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库区编码不能为空")
    private String locationCode;

    @Schema(description = "库区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "库区名称不能为空")
    private String locationName;

    @Schema(description = "仓库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2055")
    @NotNull(message = "仓库ID不能为空")
    private Long warehouseId;

    @Schema(description = "面积")
    private BigDecimal area;

    @Schema(description = "是否开启库位管理")
    private String areaFlag;

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
        return  null;
    }

}
