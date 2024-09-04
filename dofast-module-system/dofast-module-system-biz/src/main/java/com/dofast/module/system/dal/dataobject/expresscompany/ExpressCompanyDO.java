package com.dofast.module.system.dal.dataobject.expresscompany;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 租户物流公司 DO
 *
 * @author 惠智造
 */
@TableName("system_express_company")
@KeySequence("system_express_company_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressCompanyDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 物流公司id
     */
    private Integer companyId;
    /**
     * 物流公司名称
     */
    private String companyName;
    /**
     * logo
     */
    private String logo;
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
    private String fontSize;
    /**
     * 宽度
     */
    private String width;
    /**
     * 高度
     */
    private String height;
    /**
     * 真实尺寸（mm）与显示尺寸（px）的比例
     */
    private BigDecimal scale;
    /**
     * 是否支持电子面单（0不支持  1支持）
     */
    private Byte isElectronicsheet;
    /**
     * 编码（默认）
     */
    private String expressNo;
    /**
     * 编码（点三）
     */
    private String expressNoDiansan;
    /**
     * 编码（快递100）
     */
    private String expressNoKd100;
    /**
     * 编码（菜鸟）
     */
    private String expressNoCainiao;
    /**
     * 电子面单打印风格
     */
    private String printStyle;

}
