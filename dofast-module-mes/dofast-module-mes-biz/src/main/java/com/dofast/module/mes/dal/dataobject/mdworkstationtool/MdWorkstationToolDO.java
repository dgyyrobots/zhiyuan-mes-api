package com.dofast.module.mes.dal.dataobject.mdworkstationtool;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工装夹具资源 DO
 *
 * @author 芋道源码
 */
@TableName("mes_md_workstation_tool")
@KeySequence("mes_md_workstation_tool_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdWorkstationToolDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 工作站ID
     */
    private Long workstationId;
    /**
     * 工装夹具类型ID
     */
    private Long toolTypeId;
    /**
     * 类型编码
     */
    private String toolTypeCode;
    /**
     * 类型名称
     */
    private String toolTypeName;
    /**
     * 数量
     */
    private Integer quantity;
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
