package com.dofast.module.pro.dal.dataobject.andonrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 安灯呼叫记录 DO
 *
 * @author 惠智造
 */
@TableName("pro_andon_record")
@KeySequence("pro_andon_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AndonRecordDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long recordId;
    /**
     * 工作站ID
     */
    private Long workstationId;
    /**
     * 工作站编号
     */
    private String workstationCode;
    /**
     * 工作站名称
     */
    private String workstationName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 名称
     */
    private String nickName;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * 生产工单编号
     */
    private String workorderCode;
    /**
     * 生产工单名称
     */
    private String workorderName;
    /**
     * 工序ID
     */
    private Long processId;
    /**
     * 工序编号
     */
    private String processCode;
    /**
     * 工序名称
     */
    private String processName;
    /**
     * 呼叫原因
     */
    private String andonReason;
    /**
     * 级别
     */
    private String andonLevel;
    /**
     * 操作时间
     */
    private LocalDateTime operationTime;
    /**
     * 激活中
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
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;

}
