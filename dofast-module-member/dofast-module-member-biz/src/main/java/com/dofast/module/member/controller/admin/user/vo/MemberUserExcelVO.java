package com.dofast.module.member.controller.admin.user.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户 Excel VO
 *
 * @author 惠智造
 */
@Data
public class MemberUserExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("用户昵称")
    private String nickname;

    @ExcelProperty("头像")
    private String avatar;

    @ExcelProperty("状态")
    private Byte status;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("密码")
    private String password;

    @ExcelProperty("注册 IP")
    private String registerIp;

    @ExcelProperty("最后登录IP")
    private String loginIp;

    @ExcelProperty("最后登录时间")
    private LocalDateTime loginDate;

    @ExcelProperty("店铺编码")
    private String posCode;

    @ExcelProperty("渠道")
    private String refType;

    @ExcelProperty("分享人")
    private Integer shareMember;

    @ExcelProperty("推荐人")
    private Integer sourceMember;

    @ExcelProperty("分销商（分销有效）")
    private Integer fenxiaoId;

    @ExcelProperty("是否是分销商")
    private Byte isFenxiao;

    @ExcelProperty("用户等级")
    private Integer memberLevel;

    @ExcelProperty("会员等级名称")
    private String memberLevelName;

    @ExcelProperty("用户标签")
    private String memberLabel;

    @ExcelProperty("会员标签名称")
    private String memberLabelName;

    @ExcelProperty("出生日期")
    private Integer birthday;

    @ExcelProperty("积分")
    private Integer point;

    @ExcelProperty("余额")
    private BigDecimal balance;

    @ExcelProperty("成长值")
    private Integer growth;

    @ExcelProperty("现金余额(可提现)")
    private BigDecimal balanceMoney;

    @ExcelProperty("是否认证")
    private Integer isAuth;

    @ExcelProperty("是否是会员")
    private Byte isMember;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
