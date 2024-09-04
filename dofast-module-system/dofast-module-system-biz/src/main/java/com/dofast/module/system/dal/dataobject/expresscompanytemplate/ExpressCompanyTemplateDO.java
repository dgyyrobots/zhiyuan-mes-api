package com.dofast.module.system.dal.dataobject.expresscompanytemplate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统物流公司 DO
 *
 * @author 惠智造
 */
@TableName("system_express_company_template")
@KeySequence("system_express_company_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressCompanyTemplateDO extends BaseDO {

    /**
     * 公司ID
     */
    @TableId
    private Integer companyId;
    /**
     * 站点id
     */
    private Integer siteId;
    /**
     * 物流公司名称
     */
    private String companyName;
    /**
     * 物流公司logo
     */
    private String logo;
    /**
     * 物流公司网址
     */
    private String url;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 编码
     */
    private String expressNo;
    /**
     * 编码（快递100）
     */
    private String expressNoKd100;
    /**
     * 编码（菜鸟）
     */
    private String expressNoCainiao;
    /**
     * 打印内容
     */
    private String contentJson;
    /**
     * 背景图
     */
    private String backgroundImage;
    /**
     * 打印字体
     */
    private Integer fontSize;
    /**
     * 宽度
     */
    private Integer width;
    /**
     * 高度
     */
    private Integer height;
    /**
     * 真实尺寸（mm）与显示尺寸（px）的比例
     */
    private BigDecimal scale;
    /**
     * 是否支持电子面单（0不支持  1支持）
     */
    private Byte isElectronicsheet;
    /**
     * 电子面单打印风格
     */
    private String printStyle;

}
