package com.dofast.module.report.dal.dataobject.PrintLog;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 打印日志 DO
 *
 * @author a1
 */
@TableName("print_log")
@KeySequence("print_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrintLogDO extends BaseDO {

    /**
     * 打印记录ID
     */
    @TableId
    private Long id;

    /**
     * 打印编码
     */
    private String printCode;
    /**
     * 打印人
     */
    private String printName;
    /**
     * 打印类型
     */
    private String printType;

}
