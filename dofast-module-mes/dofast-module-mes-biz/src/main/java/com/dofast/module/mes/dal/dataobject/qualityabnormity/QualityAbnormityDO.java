package com.dofast.module.mes.dal.dataobject.qualityabnormity;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 品质异常信息 DO
 *
 * @author 惠智造
 */
@TableName("mes_quality_abnormity")
@KeySequence("mes_quality_abnormity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QualityAbnormityDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 产品物料编码
     */
    private String itemCode;
    /**
     * 产品物料名称
     */
    private String itemName;
    /**
     * 检验批次号
     */
    private String batchesCode;
    /**
     * 检验批次
     */
    private String batches;
    /**
     * 不良描述
     */
    private String badDescription;
    /**
     * 到货数量	
     */
    private Double quantity;
    /**
     * 不良代码
     */
    private String badCode;
    /**
     * 检测日期
     */
    private LocalDateTime inspectDate;
    /**
     * 检测人员
     */
    private String inspector;
    /**
     * 复检日期
     */
    private LocalDateTime reinspectDate;
    /**
     * 复检人员
     */
    private String reinspector;
    /**
     * 复检结论
     */
    private String reinspectConclusion;
    /**
     * 工单号
     */
    private Long orderNum;
    /**
     * 销售单号
     */
    private String saleNum;
    /**
     * 检验组
     */
    private String inspectGroup;
    /**
     * 不良数量
     */
    private Double badQuantity;

}
