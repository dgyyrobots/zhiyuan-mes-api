package com.dofast.module.qms.dal.dataobject.index;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测项 DO
 *
 * @author 芋道源码
 */
@TableName("qms_index")
@KeySequence("qms_index_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexDO extends BaseDO {

    /**
     * 检测项ID
     */
    @TableId
    private Long id;
    /**
     * 检测项编码
     */
    private String indexCode;
    /**
     * 检测项名称
     */
    private String indexName;
    /**
     * 检测项类型
     */
    private String indexType;
    /**
     * 检测工具
     */
    private String qcTool;
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
