package com.dofast.module.mes.dal.dataobject.mdclient;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_client")
@KeySequence("mes_md_client_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdClientDO extends BaseDO {

    /**
     * 客户ID
     */
    @TableId
    private Long id;
    /**
     * 客户编码
     */
    private String clientCode;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 客户简称
     */
    private String clientNick;
    /**
     * 客户英文名称
     */
    private String clientEn;
    /**
     * 客户简介
     */
    private String clientDes;
    /**
     * 客户LOGO地址
     */
    private String clientLogo;
    /**
     * 客户类型
     */
    private String clientType;
    /**
     * 客户地址
     */
    private String address;
    /**
     * 客户官网地址
     */
    private String website;
    /**
     * 客户邮箱地址
     */
    private String email;
    /**
     * 客户电话
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
