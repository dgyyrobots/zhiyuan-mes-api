package com.dofast.module.mes.controller.admin.mditemtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 物料产品分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdItemTypePageReqVO extends PageParam {

    @Schema(description = "产品物料类型编码")
    private String itemTypeCode;

    @Schema(description = "产品物料类型名称", example = "王五")
    private String itemTypeName;

    @Schema(description = "父类型ID", example = "23927")
    private Long parentTypeId;

    @Schema(description = "所有层级父节点")
    private String ancestors;

    @Schema(description = "产品物料标识")
    private String itemOrProduct;

    @Schema(description = "排列顺序")
    private Integer orderNum;

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
    private LocalDateTime createTime;

}
