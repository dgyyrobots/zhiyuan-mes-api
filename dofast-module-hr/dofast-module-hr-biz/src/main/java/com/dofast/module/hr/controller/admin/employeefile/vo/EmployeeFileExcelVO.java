package com.dofast.module.hr.controller.admin.employeefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 员工信息文件 Excel VO
 *
 * @author 惠智造
 */
@Data
public class EmployeeFileExcelVO {

    @ExcelProperty("居民身份证原件正面")
    private String idCardFront;

    @ExcelProperty("居民身份证原件反面")
    private String idCardBack;

    @ExcelProperty("毕业证原件（学历）")
    private String graduationCertificate;

    @ExcelProperty("毕业证原件（学位证书）")
    private String degreeCertificate;

    @ExcelProperty("离职证明(应届生除外)")
    private String resignationCertificate;

    @ExcelProperty("银行储蓄卡")
    private String bankCard;

    @ExcelProperty("白底一寸照")
    private String oneInchPhoto;

    @ExcelProperty("居住登记告知单/居住证")
    private String residencePermit;

    @ExcelProperty("入职承诺书签订（扫描PDF单个上传)")
    private String signingCommitmentLetter;

    @ExcelProperty("专业技术职称原件、职业资格证书原件、上岗证书原件")
    private String qualification;

    @ExcelProperty("社会保险参保证明(应届生除外)")
    private String participationCertificate;

    @ExcelProperty("近三个月三甲医院体检证明")
    private String physicalCertificate;

    @ExcelProperty("员工id")
    private Long id;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
