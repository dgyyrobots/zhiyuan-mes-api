package com.dofast.module.pro.controller.admin.processcontent.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 生产工序内容 Excel 导出 Request VO，参数和 ProcessContentPageReqVO 是一致的")
@Data
public class ProcessContentExportReqVO {

    @Schema(description = "工序ID", example = "20337")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
