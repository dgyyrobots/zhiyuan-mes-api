package com.dofast.module.pro.dal.dataobject.processcontent;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 生产工序内容 DO
 *
 * @author 芋道源码
 */
@TableName("pro_process_content")
@KeySequence("pro_process_content_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessContentDO extends BaseDO {

    /**
     * 内容ID
     */
    @TableId
    private Long id;
    /**
     * 工序ID
     */
    private Long processId;
    /**
     * 顺序编号
     */
    private Integer orderNum;
    /**
     * 内容说明
     */
    private String contentText;
    /**
     * 辅助设备
     */
    private String device;
    /**
     * 辅助材料
     */
    private String material;
    /**
     * 材料URL
     */
    private String docUrl;
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
