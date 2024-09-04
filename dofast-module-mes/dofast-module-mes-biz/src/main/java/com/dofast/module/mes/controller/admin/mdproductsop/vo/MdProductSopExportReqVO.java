package com.dofast.module.mes.controller.admin.mdproductsop.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 产品SOP Excel 导出 Request VO，参数和 MdProductSopPageReqVO 是一致的")
@Data
public class MdProductSopExportReqVO {

    @Schema(description = "物料产品ID", example = "16008")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
