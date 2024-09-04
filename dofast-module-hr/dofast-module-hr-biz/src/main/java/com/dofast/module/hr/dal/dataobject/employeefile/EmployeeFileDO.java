package com.dofast.module.hr.dal.dataobject.employeefile;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 员工信息文件 DO
 *
 * @author 惠智造
 */
@TableName("hr_employee_file")
@KeySequence("hr_employee_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFileDO extends BaseDO {

    /**
     * 居民身份证原件正面
     */
    private String idCardFront;
    /**
     * 居民身份证原件反面
     */
    private String idCardBack;
    /**
     * 毕业证原件（学历）
     */
    private String graduationCertificate;
    /**
     * 毕业证原件（学位证书）
     */
    private String degreeCertificate;
    /**
     * 离职证明(应届生除外)
     */
    private String resignationCertificate;
    /**
     * 银行储蓄卡
     */
    private String bankCard;
    /**
     * 白底一寸照
     */
    private String oneInchPhoto;
    /**
     * 居住登记告知单/居住证
     */
    private String residencePermit;
    /**
     * 入职承诺书签订（扫描PDF单个上传)
     */
    private String signingCommitmentLetter;
    /**
     * 专业技术职称原件、职业资格证书原件、上岗证书原件
     */
    private String qualification;
    /**
     * 社会保险参保证明(应届生除外)
     */
    private String participationCertificate;
    /**
     * 近三个月三甲医院体检证明
     */
    private String physicalCertificate;
    /**
     * 员工id
     */
    @TableId
    private Long id;

}
