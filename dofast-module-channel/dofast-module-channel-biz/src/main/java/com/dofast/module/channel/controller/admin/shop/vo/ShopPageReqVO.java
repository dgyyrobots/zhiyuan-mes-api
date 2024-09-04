package com.dofast.module.channel.controller.admin.shop.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 店铺授权分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopPageReqVO extends PageParam {

    @Schema(description = "店铺名称", example = "张三")
    private String displayName;

    @Schema(description = "店铺标识", example = "赵六")
    private String name;

    @Schema(description = "掌柜昵称")
    private String shopNick;

    @Schema(description = "店铺地址", example = "https://www.iocoder.cn")
    private String shopUrl;

    @Schema(description = "请求posId", example = "28029")
    private String shopId;

    @Schema(description = "请求posCode")
    private String shopCode;

    @Schema(description = "渠道")
    private String channel;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "上次拉去时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

}
