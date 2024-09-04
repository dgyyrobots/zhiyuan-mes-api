package com.dofast.module.hr.controller.admin.employeefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工信息文件更新 Request VO")
@Data
@ToString(callSuper = true)
public class EmployeeFileUpdateReqVO {

    @Schema(description = "员工id", example = "29385", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "员工id不能为空")
    private Long id;



    @Schema(description = "居民身份证原件正面")
//    @NotNull(message = "居民身份证原件正面不能为空")
    private String idCardFront;

    @Schema(description = "居民身份证原件反面")
//    @NotNull(message = "居民身份证原件反面不能为空")
    private String idCardBack;

    @Schema(description = "毕业证原件（学历）")
//    @NotNull(message = "毕业证原件（学历）不能为空")
    private String graduationCertificate;

    @Schema(description = "毕业证原件（学位证书）")
//    @NotNull(message = "毕业证原件（学位证书）不能为空")
    private String degreeCertificate;

    @Schema(description = "离职证明(应届生除外)")
//    @NotNull(message = "离职证明(应届生除外)不能为空")
    private String resignationCertificate;

    @Schema(description = "银行储蓄卡")
//    @NotNull(message = "银行储蓄卡不能为空")
    private String bankCard;

    @Schema(description = "白底一寸照")
//    @NotNull(message = "白底一寸照不能为空")
    private String oneInchPhoto;

    @Schema(description = "居住登记告知单/居住证")
//    @NotNull(message = "居住登记告知单/居住证不能为空")
    private String residencePermit;

    @Schema(description = "入职承诺书签订（扫描PDF单个上传)")
//    @NotNull(message = "入职承诺书签订（扫描PDF单个上传)不能为空")
    private String signingCommitmentLetter;

    @Schema(description = "专业技术职称原件、职业资格证书原件、上岗证书原件")
//    @NotNull(message = "专业技术职称原件、职业资格证书原件、上岗证书原件不能为空")
    private String qualification;

    @Schema(description = "社会保险参保证明(应届生除外)")
//    @NotNull(message = "社会保险参保证明(应届生除外)不能为空")
    private String participationCertificate;

    @Schema(description = "近三个月三甲医院体检证明")
    private String physicalCertificate;

}
