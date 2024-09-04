package com.dofast.module.wms.dal.dataobject.package1;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 装箱单 DO
 *
 * @author 芋道源码
 */
@TableName("wms_package")
@KeySequence("wms_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDO extends BaseDO {

    /**
     * 装箱单ID
     */
    @TableId
    private Long id;
    /**
     * 父箱ID
     */
    private Long parentId;
    /**
     * 所有父节点ID
     */
    private String ancestors;
    /**
     * 装箱单编号
     */
    private String packageCode;
    /**
     * 条码ID
     */
    private Long barcodeId;
    /**
     * 条码内容
     */
    private String barcodeContent;
    /**
     * 条码地址
     */
    private String barcodeUrl;
    /**
     * 装箱日期
     */
    private LocalDateTime packageDate;
    /**
     * 销售订单编号
     */
    private String soCode;
    /**
     * 发票编号
     */
    private String invoiceCode;
    /**
     * 客户ID
     */
    private Long clientId;
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
     * 箱长度
     */
    private BigDecimal packageLength;
    /**
     * 箱宽度
     */
    private BigDecimal packageWidth;
    /**
     * 箱高度
     */
    private BigDecimal packageHeight;
    /**
     * 尺寸单位
     */
    private String sizeUnit;
    /**
     * 净重
     */
    private BigDecimal netWeight;
    /**
     * 毛重
     */
    private BigDecimal crossWeight;
    /**
     * 重量单位
     */
    private String weightUnit;
    /**
     * 检查员用户名
     */
    private String inspector;
    /**
     * 检查员名称
     */
    private String inspectorName;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否生效
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
