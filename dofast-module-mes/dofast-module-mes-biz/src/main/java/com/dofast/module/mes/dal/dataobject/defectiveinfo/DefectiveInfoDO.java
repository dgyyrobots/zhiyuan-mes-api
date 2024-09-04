package com.dofast.module.mes.dal.dataobject.defectiveinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 不良品信息管理 DO
 *
 * @author 惠智造
 */
@TableName("mes_defective_info")
@KeySequence("mes_defective_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefectiveInfoDO extends BaseDO {

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
     * 不良数量
     */
    private Double badQuantity;
    /**
     * 抽检数量
     */
    private Double inspectQty;
    /**
     * 故障码名称
     */
    private String errorCodeName;
    /**
     * 不良发生时间
     */
    private LocalDateTime badTime;
    /**
     * 录入人
     */
    private String entryPerson;
    /**
     * 录入时间
     */
    private LocalDateTime entryTime;
    /**
     * 复判人
     */
    private String reinspector;
    /**
     * 复检日期
     */
    private LocalDateTime reinspectDate;
    /**
     * 复检结论
     */
    private String reinspectConclusion;
    /**
     * 检验组
     */
    private String inspectGroup;
    /**
     * 执行状态
     */
    private String excuteState;
    /**
     * 执行状态代码
     */
    private String excuteCode;

}
