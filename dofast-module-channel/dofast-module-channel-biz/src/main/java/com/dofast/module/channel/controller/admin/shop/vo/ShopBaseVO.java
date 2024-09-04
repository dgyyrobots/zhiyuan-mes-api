package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 店铺授权 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ShopBaseVO {

    @Schema(description = "店铺名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "店铺名称不能为空")
    private String displayName;

    @Schema(description = "店铺标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "店铺标识不能为空")
    private String name;

    @Schema(description = "掌柜昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "掌柜昵称不能为空")
    private String shopNick;

    @Schema(description = "店铺地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotNull(message = "店铺地址不能为空")
    private String shopUrl;

    @Schema(description = "请求posId", requiredMode = Schema.RequiredMode.REQUIRED, example = "28029")
    @NotNull(message = "请求posId不能为空")
    private String shopId;

    @Schema(description = "请求posCode", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "请求posCode不能为空")
    private String shopCode;

    @Schema(description = "银行卡号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "银行卡号不能为空")
    private String bankCard;

    @Schema(description = "渠道", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "渠道不能为空")
    private String channel;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "上次拉去时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上次拉去时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime lastTime;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "随便")
    @NotNull(message = "备注不能为空")
    private String remark;

}
