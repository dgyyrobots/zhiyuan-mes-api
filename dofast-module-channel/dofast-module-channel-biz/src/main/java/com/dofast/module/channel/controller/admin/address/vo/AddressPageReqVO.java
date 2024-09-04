package com.dofast.module.channel.controller.admin.address.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 交易客户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AddressPageReqVO extends PageParam {

    @Schema(description = "相关单ID", example = "15544")
    private String refOid;

    @Schema(description = "店铺编码")
    private String posCode;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "渠道", example = "2")
    private String refType;

    @Schema(description = "收货人国家")
    private String receiverCountry;

    @Schema(description = "收货人省")
    private String receiverState;

    @Schema(description = "收货人市")
    private String receiverCity;

    @Schema(description = "收件人区/县")
    private String receiverDistrict;

    @Schema(description = "收货人镇")
    private String receiverTown;

    @Schema(description = "收货人ID字段，可用于区分多个订单是否属于同一个收货人", example = "26583")
    private String receiverId;

    @Schema(description = "买家昵称")
    private String openBuyerNick;

    @Schema(description = "买家昵称", example = "30898")
    private String openBuyerId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "关联收货地址ID", example = "26690")
    private Integer addressId;

    @Schema(description = "关联用户ID", example = "16979")
    private Integer userId;

}
