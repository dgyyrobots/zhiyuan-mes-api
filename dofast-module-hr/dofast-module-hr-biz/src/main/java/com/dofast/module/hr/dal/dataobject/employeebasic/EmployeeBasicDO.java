package com.dofast.module.hr.dal.dataobject.employeebasic;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 员工基本信息 DO
 *
 * @author 惠智造
 */
@TableName("hr_employee_basic")
@KeySequence("hr_employee_basic_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBasicDO extends BaseDO {

    /**
     * 形象照
     */
    private String employeePhoto;
    /**
     * 姓名
     */
    private String employeeRealname;
    /**
     * 性别
     *
     * 枚举 {@link TODO system_user_sex 对应的类}
     */
    private String employeeSex;
    /**
     * 婚姻状态
     *
     * 枚举 {@link TODO hr_employee_married 对应的类}
     */
    private String employeeMarried;
    /**
     * 政治身份
     */
    private String employeePolitics;
    /**
     * 祖籍
     */
    private String employeeNativeplace;
    /**
     * 生日
     */
    private Date employeeBirthday;
    /**
     * 身高
     */
    private String employeeHeight;
    /**
     * 户籍省份id
     */
    private Integer householdProvinceId;
    /**
     * 户籍省份
     */
    private String householdProvinceName;
    /**
     * 国籍
     */
    private String householdNature;
    /**
     * 户籍市id
     */
    private Integer householdCityId;
    /**
     * 户籍地址
     */
    private String householdAddress;
    /**
     * 户籍市
     */
    private String householdCityName;
    /**
     * 体重
     */
    private String employeeWeight;
    /**
     * 血型
     *
     * 枚举 {@link TODO blood_type 对应的类}
     */
    private String bloodtype;
    /**
     * 毕业院校
     */
    private String educationName;
    /**
     * 学历
     */
    private String education;
    /**
     * 邮箱
     */
    private String emails;
    /**
     * qq
     */
    private String qq;
    /**
     * 身份号
     */
    private String idNo;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 工资卡姓名
     */
    private String cardName;
    /**
     * 工资卡号
     */
    private String cardNo;
    /**
     * 工资卡开户行
     */
    private String cardAddress;
    /**
     * 现居住省份id
     */
    private Integer nowProvinceId;
    /**
     * 现居住省份
     */
    private String nowProvinceName;
    /**
     * 现居住地址
     */
    private String nowProvinceAddress;
    /**
     * 现居住市id
     */
    private Integer nowCityId;
    /**
     * 现居住市
     */
    private String nowCityName;
    /**
     * 紧急联系人
     */
    private String urgentNameOne;
    /**
     * 紧急联系人电话
     */
    private String urgentNameOnePhone;
    /**
     * 爱好
     */
    private String specialty;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门id
     */
    private Integer depId;
    /**
     * 部门
     */
    private String depName;
    /**
     * 职位id
     */
    private Integer positionId;
    /**
     * 职位
     */
    private String positionName;
    /**
     * 花名
     */
    private String nickName;
    /**
     * 头像
     */
    private String nickImg;
    /**
     * 毕业后首次缴纳社保
     */
    private LocalDate socialPaymentTime;
    /**
     * 阴历生日
     */
    private Date dateOfBirthTime;
    /**
     * 工作年限
     */
    private String workAge;
    /**
     * 职称
     */
    private String workLevel;
    /**
     * 入职时间
     */
    private LocalDate entryTime;
    /**
     * 转正时间
     */
    private LocalDate workerTime;
    /**
     * 入职工作区域
     */
    private String workName;
    /**
     * 员工id
     */
    @TableId
    private Long id;
    /**
     * varchar
     */
    private String ethnic;
    /**
     * 性格
     */
    private String chara;
    /**
     * 状态
     */
    private String stus;

}
