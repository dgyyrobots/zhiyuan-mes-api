package com.dofast.module.hr.controller.admin.employeefile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工信息文件创建 Request VO")
@Data
@ToString(callSuper = true)
public class EmployeeFileCreateReqVO {
    @Schema(description = "居民身份证原件正面")
    private String idCardFront;

    @Schema(description = "居民身份证原件反面")
    private String idCardBack;

    @Schema(description = "毕业证原件（学历）")
    private String graduationCertificate;

    @Schema(description = "毕业证原件（学位证书）")
    private String degreeCertificate;

    @Schema(description = "离职证明(应届生除外)")
    private String resignationCertificate;

    @Schema(description = "银行储蓄卡")
    private String bankCard;

    @Schema(description = "白底一寸照")
    private String oneInchPhoto;

    @Schema(description = "居住登记告知单/居住证")
    private String residencePermit;

    @Schema(description = "入职承诺书签订（扫描PDF单个上传)")
    private String signingCommitmentLetter;

    @Schema(description = "专业技术职称原件、职业资格证书原件、上岗证书原件")
    private String qualification;

    @Schema(description = "社会保险参保证明(应届生除外)")
    private String participationCertificate;

    @Schema(description = "近三个月三甲医院体检证明")
    private String physicalCertificate;
}
