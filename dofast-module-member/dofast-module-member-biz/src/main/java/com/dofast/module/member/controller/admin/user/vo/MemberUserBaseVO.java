package com.dofast.module.member.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;


import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * 会员用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberUserBaseVO {

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "15601691300")
    @NotNull(message = "手机号不能为空")
    private String mobile;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
//    @NotNull(message = "状态不能为空")
    private Byte status;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "用户昵称不能为空")
    private String nickname;

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn/x.png")
    @URL(message = "头像必须是 URL 格式")
    private String avatar;

    @Schema(description = "用户昵称", example = "李四")
    private String name;


    @Schema(description = "用户性别", example = "1")
    private Byte sex;


    @Schema(description = "所在地编号", example = "4371")
    private Long areaId;

    @Schema(description = "所在地全程", example = "上海上海市普陀区")
    private String areaName;

    @Schema(description = "出生日期", example = "2023-03-12")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDateTime birthday;

    @Schema(description = "会员备注", example = "我是小备注")
    private String mark;

    @Schema(description = "会员标签", example = "[1, 2]")
    private List<Long> tagIds;

    @Schema(description = "会员等级编号", example = "1")
    private Long levelId;

    @Schema(description = "用户分组编号", example = "1")
    private Long groupId;

    @Schema(description = "分享人", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer shareMember;

    @Schema(description = "推荐人", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sourceMember;

    @Schema(description = "分销商（分销有效）", requiredMode = Schema.RequiredMode.REQUIRED, example = "3878")
    private Integer fenxiaoId;

    @Schema(description = "是否是分销商", requiredMode = Schema.RequiredMode.REQUIRED)
    private Byte isFenxiao;

    @Schema(description = "用户等级", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer memberLevel;

    @Schema(description = "会员等级名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    private String memberLevelName;

    @Schema(description = "用户标签", requiredMode = Schema.RequiredMode.REQUIRED)
    private String memberLabel;

    @Schema(description = "会员标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    private String memberLabelName;

    @Schema(description = "积分", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer point;

    @Schema(description = "余额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal balance;

    @Schema(description = "成长值", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer growth;

    @Schema(description = "现金余额(可提现)", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal balanceMoney;

    @Schema(description = "是否认证", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isAuth;

    @Schema(description = "是否是会员", requiredMode = Schema.RequiredMode.REQUIRED)
    private Byte isMember;



}
