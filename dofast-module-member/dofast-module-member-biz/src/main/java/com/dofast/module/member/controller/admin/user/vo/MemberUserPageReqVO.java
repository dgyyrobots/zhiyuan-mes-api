package com.dofast.module.member.controller.admin.user.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;



import java.math.BigDecimal;



import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberUserPageReqVO extends PageParam {



    @Schema(description = "ID")
    private Long id;


    @Schema(description = "手机号", example = "15601691300")
    private String mobile;

    @Schema(description = "用户昵称", example = "李四")
    private String nickname;

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

    @Schema(description = "状态")
    private String status;


    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "会员标签编号列表", example = "[1, 2]")
    private List<Long> tagIds;

    @Schema(description = "会员等级编号", example = "1")
    private Long levelId;

    @Schema(description = "用户分组编号", example = "1")
    private Long groupId;

    // TODO 芋艿：注册用户类型；

    // TODO 芋艿：登录用户类型；

}
