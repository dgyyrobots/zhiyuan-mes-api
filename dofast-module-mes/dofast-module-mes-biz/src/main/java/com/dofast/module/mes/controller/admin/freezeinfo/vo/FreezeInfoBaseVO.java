package com.dofast.module.mes.controller.admin.freezeinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 产品冻结信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FreezeInfoBaseVO {

    @Schema(description = "产品物料编码", example = "测试料号1")
    private String itemCode;

    @Schema(description = "产品物料名称", example = "赵六")
    private String itemName;

    @Schema(description = "产品物料数量", example = "500")
    private Long itenQty;

    @Schema(description = "状态(1-解冻, 2-冻结)", example = "1")
    private Long state;

    @Schema(description = "仓库编号", example = "仓库001")
    private String whCode;

    @Schema(description = "库位", example = "库位1")
    private String storageCode;

    @Schema(description = "冻结原因", example = "你猜")
    private String freezeMemo;

    @Schema(description = "冻结人员")
    private String freezer;

    @Schema(description = "冻结时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime freezeTime;

    @Schema(description = "解冻人员")
    private String thawPerson;

    @Schema(description = "解冻原因", example = "你猜")
    private String thawMemo;

    @Schema(description = "解冻时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime thawTime;

    @Schema(description = "产品物料SN", example = "测试物料SN")
    private String itenSn;

}
