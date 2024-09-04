package com.dofast.module.hr.controller.admin.employeebasic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 员工基本信息 Excel VO
 *
 * @author 惠智造
 */
@Data
public class EmployeeBasicExcelVO {

    @ExcelProperty("形象照")
    private String employeePhoto;

    @ExcelProperty("姓名")
    private String employeeRealname;

    @ExcelProperty(value = "性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String employeeSex;

    @ExcelProperty(value = "婚姻状态", converter = DictConvert.class)
    @DictFormat("hr_employee_married") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String employeeMarried;

    @ExcelProperty("政治身份")
    private String employeePolitics;

    @ExcelProperty("祖籍")
    private String employeeNativeplace;

    @ExcelProperty("生日")
    private LocalDate employeeBirthday;

    @ExcelProperty("身高")
    private String employeeHeight;

    @ExcelProperty("户籍省份id")
    private Integer householdProvinceId;

    @ExcelProperty("户籍省份")
    private String householdProvinceName;

    @ExcelProperty("国籍")
    private String householdNature;

    @ExcelProperty("户籍市id")
    private Integer householdCityId;

    @ExcelProperty("户籍地址")
    private String householdAddress;

    @ExcelProperty("户籍市")
    private String householdCityName;

    @ExcelProperty("体重")
    private String employeeWeight;

    @ExcelProperty(value = "血型", converter = DictConvert.class)
    @DictFormat("blood_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String bloodtype;

    @ExcelProperty("毕业院校")
    private String educationName;

    @ExcelProperty("学历")
    private String education;

    @ExcelProperty("邮箱")
    private String emails;

    @ExcelProperty("qq")
    private String qq;

    @ExcelProperty("身份号")
    private String idNo;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("工资卡姓名")
    private String cardName;

    @ExcelProperty("工资卡号")
    private String cardNo;

    @ExcelProperty("工资卡开户行")
    private String cardAddress;

    @ExcelProperty("现居住省份id")
    private Integer nowProvinceId;

    @ExcelProperty("现居住省份")
    private String nowProvinceName;

    @ExcelProperty("现居住地址")
    private String nowProvinceAddress;

    @ExcelProperty("现居住市id")
    private Integer nowCityId;

    @ExcelProperty("现居住市")
    private String nowCityName;

    @ExcelProperty("紧急联系人")
    private String urgentNameOne;

    @ExcelProperty("紧急联系人电话")
    private String urgentNameOnePhone;

    @ExcelProperty("爱好")
    private String specialty;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("部门id")
    private Integer depId;

    @ExcelProperty("部门")
    private String depName;

    @ExcelProperty("职位id")
    private Integer positionId;

    @ExcelProperty("职位")
    private String positionName;

    @ExcelProperty("花名")
    private String nickName;

    @ExcelProperty("毕业后首次缴纳社保")
    private LocalDate socialPaymentTime;

    @ExcelProperty("阴历生日")
    private LocalDate dateOfBirthTime;

    @ExcelProperty("工作年限")
    private String workAge;

    @ExcelProperty("职称")
    private String workLevel;

    @ExcelProperty("入职时间")
    private LocalDate entryTime;

    @ExcelProperty("转正时间")
    private LocalDate workerTime;

    @ExcelProperty("入职工作区域")
    private String workName;

    @ExcelProperty("员工id")
    private Long id;

    @ExcelProperty("varchar")
    private String ethnic;

    @ExcelProperty("性格")
    private String chara;

    @ExcelProperty("状态")
    private String stus;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
