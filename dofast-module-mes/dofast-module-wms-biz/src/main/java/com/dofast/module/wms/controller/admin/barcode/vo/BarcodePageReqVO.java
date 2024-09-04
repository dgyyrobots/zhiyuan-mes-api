package com.dofast.module.wms.controller.admin.barcode.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 条码清单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarcodePageReqVO extends PageParam {

    @Schema(description = "条码格式")
    private String barcodeFormart;

    @Schema(description = "条码类型", example = "1")
    private String barcodeType;

    @Schema(description = "条码内容")
    private String barcodeContent;

    @Schema(description = "业务ID", example = "30943")
    private Long bussinessId;

    @Schema(description = "业务编码")
    private String bussinessCode;

    @Schema(description = "业务名称", example = "王五")
    private String bussinessName;

    @Schema(description = "条码地址", example = "https://www.iocoder.cn")
    private String barcodeUrl;

    @Schema(description = "是否生效")
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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
