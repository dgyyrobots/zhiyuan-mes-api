package com.dofast.module.pro.dal.dataobject.route;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工艺路线 DO
 *
 * @author 芋道源码
 */
@TableName("pro_route")
@KeySequence("pro_route_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDO extends BaseDO {

    /**
     * 工艺路线ID
     */
    @TableId
    private Long id;
    /**
     * 工艺路线编号
     */
    private String routeCode;
    /**
     * 工艺路线名称
     */
    private String routeName;
    /**
     * 工艺路线说明
     */
    private String routeDesc;
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
