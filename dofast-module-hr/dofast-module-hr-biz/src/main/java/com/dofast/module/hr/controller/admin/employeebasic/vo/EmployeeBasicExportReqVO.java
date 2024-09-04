package com.dofast.module.hr.controller.admin.employeebasic.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 员工基本信息 Excel 导出 Request VO，参数和 EmployeeBasicPageReqVO 是一致的")
@Data
public class EmployeeBasicExportReqVO {

    @Schema(description = "形象照")
    private String employeePhoto;

    @Schema(description = "姓名", example = "芋艿")
    private String employeeRealname;

    @Schema(description = "性别")
    private String employeeSex;

    @Schema(description = "婚姻状态")
    private String employeeMarried;

    @Schema(description = "政治身份")
    private String employeePolitics;

    @Schema(description = "祖籍")
    private String employeeNativeplace;

    @Schema(description = "生日")
    private LocalDate employeeBirthday;

    @Schema(description = "身高")
    private String employeeHeight;

    @Schema(description = "户籍省份id", example = "26065")
    private Integer householdProvinceId;

    @Schema(description = "户籍省份", example = "李四")
    private String householdProvinceName;

    @Schema(description = "国籍")
    private String householdNature;

    @Schema(description = "户籍市id", example = "8232")
    private Integer householdCityId;

    @Schema(description = "户籍地址")
    private String householdAddress;

    @Schema(description = "户籍市", example = "张三")
    private String householdCityName;

    @Schema(description = "体重")
    private String employeeWeight;

    @Schema(description = "血型", example = "2")
    private String bloodtype;

    @Schema(description = "毕业院校", example = "芋艿")
    private String educationName;

    @Schema(description = "学历")
    private String education;

    @Schema(description = "邮箱")
    private String emails;

    @Schema(description = "qq")
    private String qq;

    @Schema(description = "身份号")
    private String idNo;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "工资卡姓名", example = "李四")
    private String cardName;

    @Schema(description = "工资卡号")
    private String cardNo;

    @Schema(description = "工资卡开户行")
    private String cardAddress;

    @Schema(description = "现居住省份id", example = "10579")
    private Integer nowProvinceId;

    @Schema(description = "现居住省份", example = "李四")
    private String nowProvinceName;

    @Schema(description = "现居住地址")
    private String nowProvinceAddress;

    @Schema(description = "现居住市id", example = "11121")
    private Integer nowCityId;

    @Schema(description = "现居住市", example = "芋艿")
    private String nowCityName;

    @Schema(description = "紧急联系人")
    private String urgentNameOne;

    @Schema(description = "紧急联系人电话")
    private String urgentNameOnePhone;

    @Schema(description = "爱好")
    private String specialty;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "部门id", example = "25423")
    private Integer depId;

    @Schema(description = "部门", example = "芋艿")
    private String depName;

    @Schema(description = "职位id", example = "29980")
    private Integer positionId;

    @Schema(description = "职位", example = "张三")
    private String positionName;

    @Schema(description = "花名", example = "李四")
    private String nickName;

    @Schema(description = "毕业后首次缴纳社保")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] socialPaymentTime;

    @Schema(description = "阴历生日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] dateOfBirthTime;

    @Schema(description = "工作年限")
    private String workAge;

    @Schema(description = "职称")
    private String workLevel;

    @Schema(description = "入职时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] entryTime;

    @Schema(description = "转正时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] workerTime;

    @Schema(description = "入职工作区域", example = "王五")
    private String workName;

    @Schema(description = "varchar")
    private String ethnic;

    @Schema(description = "性格")
    private String chara;

    @Schema(description = "状态")
    private String stus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
