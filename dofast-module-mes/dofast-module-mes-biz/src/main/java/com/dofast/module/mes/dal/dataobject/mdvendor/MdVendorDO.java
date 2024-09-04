package com.dofast.module.mes.dal.dataobject.mdvendor;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 供应商 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_vendor")
@KeySequence("mes_md_vendor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdVendorDO extends BaseDO {

    /**
     * 供应商ID
     */
    @TableId
    private Long id;
    /**
     * 供应商编码
     */
    private String vendorCode;
    /**
     * 供应商名称
     */
    private String vendorName;
    /**
     * 供应商简称
     */
    private String vendorNick;
    /**
     * 供应商英文名称
     */
    private String vendorEn;
    /**
     * 供应商简介
     */
    private String vendorDes;
    /**
     * 供应商LOGO地址
     */
    private String vendorLogo;
    /**
     * 供应商等级
     */
    private String vendorLevel;
    /**
     * 供应商评分
     */
    private Integer vendorScore;
    /**
     * 供应商地址
     */
    private String address;
    /**
     * 供应商官网地址
     */
    private String website;
    /**
     * 供应商邮箱地址
     */
    private String email;
    /**
     * 供应商电话
     */
    private String tel;
    /**
     * 联系人1
     */
    private String contact1;
    /**
     * 联系人1-电话
     */
    private String contact1Tel;
    /**
     * 联系人1-邮箱
     */
    private String contact1Email;
    /**
     * 联系人2
     */
    private String contact2;
    /**
     * 联系人2-电话
     */
    private String contact2Tel;
    /**
     * 联系人2-邮箱
     */
    private String contact2Email;
    /**
     * 统一社会信用代码
     */
    private String creditCode;
    /**
     * 是否启用
     */
    private String enableFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预留字段1
     */
    private String attr1;
    /**
     * 预留字段2
     */
    private String attr2;
    /**
     * 预留字段3
     */
    private Integer attr3;
    /**
     * 预留字段4
     */
    private Integer attr4;

}
