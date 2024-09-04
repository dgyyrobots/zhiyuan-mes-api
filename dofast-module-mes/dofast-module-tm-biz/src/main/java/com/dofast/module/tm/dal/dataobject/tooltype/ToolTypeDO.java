package com.dofast.module.tm.dal.dataobject.tooltype;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工装夹具类型 DO
 *
 * @author 惠智造
 */
@TableName("tm_tool_type")
@KeySequence("tm_tool_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolTypeDO extends BaseDO {

    /**
     * 工装夹具类型ID
     */
    @TableId
    private Long id;
    /**
     * 类型编码
     */
    private String toolTypeCode;
    /**
     * 类型名称
     */
    private String toolTypeName;
    /**
     * 是否编码管理
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private String codeFlag;
    /**
     * 保养维护类型
     *
     * 枚举 {@link TODO tm-fix-type 对应的类}
     */
    private String maintenType;
    /**
     * 保养周期
     */
    private Integer maintenPeriod;
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
