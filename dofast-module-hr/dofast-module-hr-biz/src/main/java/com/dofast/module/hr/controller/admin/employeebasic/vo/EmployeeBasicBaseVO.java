package com.dofast.module.hr.controller.admin.employeebasic.vo;

import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 员工基本信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EmployeeBasicBaseVO extends BaseDO {

    @Schema(description = "形象照", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "形象照不能为空")
    private String employeePhoto;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "姓名不能为空")
    private String employeeRealname;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "性别不能为空")
    private String employeeSex;

    @Schema(description = "婚姻状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "婚姻状态不能为空")
    private String employeeMarried;

    @Schema(description = "政治身份", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "政治身份不能为空")
    private String employeePolitics;

    @Schema(description = "祖籍", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "祖籍不能为空")
    private String employeeNativeplace;

    @Schema(description = "生日", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生日不能为空")
    private Date employeeBirthday;

    @Schema(description = "身高", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "身高不能为空")
    private String employeeHeight;

    @Schema(description = "户籍省份id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26065")
    @NotNull(message = "户籍省份id不能为空")
    private Integer householdProvinceId;

    @Schema(description = "户籍省份", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "户籍省份不能为空")
    private String householdProvinceName;

    @Schema(description = "国籍", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "国籍不能为空")
    private String householdNature;

    @Schema(description = "户籍市id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8232")
    @NotNull(message = "户籍市id不能为空")
    private Integer householdCityId;

    @Schema(description = "户籍地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "户籍地址不能为空")
    private String householdAddress;

    @Schema(description = "户籍市", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "户籍市不能为空")
    private String householdCityName;

    @Schema(description = "体重", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "体重不能为空")
    private String employeeWeight;

    @Schema(description = "血型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "血型不能为空")
    private String bloodtype;

    @Schema(description = "毕业院校", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "毕业院校不能为空")
    private String educationName;

    @Schema(description = "学历", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "学历不能为空")
    private String education;

    @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "邮箱不能为空")
    private String emails;

    @Schema(description = "qq", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "qq不能为空")
    private String qq;

    @Schema(description = "身份号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "身份号不能为空")
    private String idNo;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "手机号不能为空")
    private String phone;

    @Schema(description = "工资卡姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "工资卡姓名不能为空")
    private String cardName;

    @Schema(description = "工资卡号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工资卡号不能为空")
    private String cardNo;

    @Schema(description = "工资卡开户行", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工资卡开户行不能为空")
    private String cardAddress;

    @Schema(description = "现居住省份id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10579")
    @NotNull(message = "现居住省份id不能为空")
    private Integer nowProvinceId;

    @Schema(description = "现居住省份", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "现居住省份不能为空")
    private String nowProvinceName;

    @Schema(description = "现居住地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "现居住地址不能为空")
    private String nowProvinceAddress;

    @Schema(description = "现居住市id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11121")
    @NotNull(message = "现居住市id不能为空")
    private Integer nowCityId;

    @Schema(description = "现居住市", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "现居住市不能为空")
    private String nowCityName;

    @Schema(description = "紧急联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "紧急联系人不能为空")
    private String urgentNameOne;

    @Schema(description = "紧急联系人电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "紧急联系人电话不能为空")
    private String urgentNameOnePhone;

    @Schema(description = "爱好", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "爱好不能为空")
    private String specialty;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "你猜")
    @NotNull(message = "备注不能为空")
    private String remark;

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "25423")
    @NotNull(message = "部门id不能为空")
    private Integer depId;

    @Schema(description = "部门", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "部门不能为空")
    private String depName;

    @Schema(description = "职位id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29980")
    @NotNull(message = "职位id不能为空")
    private Integer positionId;

    @Schema(description = "职位", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "职位不能为空")
    private String positionName;

    @Schema(description = "花名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "花名不能为空")
    private String nickName;

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    private String nickImg;

    @Schema(description = "毕业后首次缴纳社保", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "毕业后首次缴纳社保不能为空")
    private Date socialPaymentTime;

    @Schema(description = "阴历生日", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "阴历生日不能为空")
    private Date dateOfBirthTime;

    @Schema(description = "工作年限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工作年限不能为空")
    private String workAge;

    @Schema(description = "职称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "职称不能为空")
    private String workLevel;

    @Schema(description = "入职时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入职时间不能为空")
    private Date entryTime;

    @Schema(description = "转正时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "转正时间不能为空")
    private Date workerTime;

    @Schema(description = "入职工作区域", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "入职工作区域不能为空")
    private String workName;

    @Schema(description = "varchar", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "varchar不能为空")
    private String ethnic;

    @Schema(description = "性格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "性格不能为空")
    private String chara;

    @Schema(description = "状态")
    private String stus;

}
