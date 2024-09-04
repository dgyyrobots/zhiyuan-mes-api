package com.dofast.module.tm.dal.dataobject.tool;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工装夹具清单 DO
 *
 * @author 惠智造
 */
@TableName("tm_tool")
@KeySequence("tm_tool_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolDO extends BaseDO {

    /**
     * 工装夹具ID
     */
    @TableId
    private Long id;
    /**
     * 工装夹具编码
     */
    private String toolCode;
    /**
     * 工装夹具名称
     */
    private String toolName;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 型号
     */
    private String spec;
    /**
     * 工装夹具类型ID
     */
    private Long toolTypeId;
    /**
     * 工装夹具类型编码
     */
    private String toolTypeCode;
    /**
     * 工装夹具类型名称
     */
    private String toolTypeName;
    /**
     * 是否单独编码管理
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private String codeFlag;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 可用数量
     */
    private Integer quantityAvail;
    /**
     * 保养维护类型
     */
    private String maintenType;
    /**
     * 下一次保养周期
     */
    private Integer nextMaintenPeriod;
    /**
     * 下一次保养日期
     */
    private LocalDateTime nextMaintenDate;
    /**
     * 状态[MES_TOOL_STATUS]
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private String status;
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
