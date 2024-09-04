package com.dofast.module.wms.controller.admin.barcodeconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 条码配置 Excel 导出 Request VO，参数和 BarcodeConfigPageReqVO 是一致的")
@Data
public class BarcodeConfigExportReqVO {

    @Schema(description = "条码格式")
    private String barcodeFormart;

    @Schema(description = "条码类型", example = "1")
    private String barcodeType;

    @Schema(description = "内容格式")
    private String contentFormart;

    @Schema(description = "内容样例")
    private String contentExample;

    @Schema(description = "是否自动生成")
    private String autoGenFlag;

    @Schema(description = "默认的打印模板")
    private String defaultTemplate;

    @Schema(description = "是否生效")
    private String enableFlag;

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
