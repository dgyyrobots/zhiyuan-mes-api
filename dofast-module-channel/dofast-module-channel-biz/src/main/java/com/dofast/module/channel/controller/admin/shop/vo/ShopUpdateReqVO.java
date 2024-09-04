package com.dofast.module.channel.controller.admin.shop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 店铺授权更新 Request VO")
@Data
@ToString(callSuper = true)
public class ShopUpdateReqVO  {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15265")
    @NotNull(message = "ID不能为空")
    private String id;

    @Schema(description = "排序号")
//    @NotNull(message = "排序号不能为空")
    private Integer sortCode;


    @Schema(description = "店铺名称", example = "张三")
//    @NotNull(message = "店铺名称不能为空")
    private String displayName;

    @Schema(description = "店铺标识", example = "赵六")
//    @NotNull(message = "店铺标识不能为空")
    private String name;

    @Schema(description = "掌柜昵称")
//    @NotNull(message = "掌柜昵称不能为空")
    private String shopNick;

    @Schema(description = "店铺地址", example = "https://www.iocoder.cn")
//    @NotNull(message = "店铺地址不能为空")
    private String shopUrl;

    @Schema(description = "请求posId", example = "28029")
//    @NotNull(message = "请求posId不能为空")
    private String shopId;

    @Schema(description = "请求posCode")
//    @NotNull(message = "请求posCode不能为空")
    private String shopCode;

    @Schema(description = "银行卡号")
//    @NotNull(message = "银行卡号不能为空")
    private String bankCard;

    @Schema(description = "渠道")
//    @NotNull(message = "渠道不能为空")
    private String channel;

    @Schema(description = "状态")
//    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "上次拉去时间")
//    @NotNull(message = "上次拉去时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime lastTime;

    @Schema(description = "备注", example = "随便")
//    @NotNull(message = "备注不能为空")
    private String remark;

}
