package com.dofast.module.member.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户 Excel 导出 Request VO，参数和 MemberUserPageReqVO 是一致的")
@Data
public class MemberUserExportReqVO {

    @Schema(description = "用户昵称", example = "芋艿")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "状态", example = "2")
    private Byte status;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "注册 IP")
    private String registerIp;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] loginDate;

    @Schema(description = "店铺编码")
    private String posCode;

    @Schema(description = "渠道", example = "2")
    private String refType;

    @Schema(description = "分享人")
    private Integer shareMember;

    @Schema(description = "推荐人")
    private Integer sourceMember;

    @Schema(description = "分销商（分销有效）", example = "3878")
    private Integer fenxiaoId;

    @Schema(description = "是否是分销商")
    private Byte isFenxiao;

    @Schema(description = "用户等级")
    private Integer memberLevel;

    @Schema(description = "会员等级名称", example = "李四")
    private String memberLevelName;

    @Schema(description = "用户标签")
    private String memberLabel;

    @Schema(description = "会员标签名称", example = "王五")
    private String memberLabelName;

    @Schema(description = "出生日期")
    private Integer birthday;

    @Schema(description = "积分")
    private Integer point;

    @Schema(description = "余额")
    private BigDecimal balance;

    @Schema(description = "成长值")
    private Integer experience;

    @Schema(description = "现金余额(可提现)")
    private BigDecimal balanceMoney;

    @Schema(description = "是否认证")
    private Integer isAuth;

    @Schema(description = "是否是会员")
    private Byte isMember;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
