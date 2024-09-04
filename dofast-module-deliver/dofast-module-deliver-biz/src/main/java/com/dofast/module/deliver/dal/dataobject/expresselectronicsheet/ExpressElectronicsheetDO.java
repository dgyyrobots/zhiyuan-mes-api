package com.dofast.module.deliver.dal.dataobject.expresselectronicsheet;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电子面单 DO
 *
 * @author a1
 */
@TableName("deliver_express_electronicsheet")
@KeySequence("deliver_express_electronicsheet_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressElectronicsheetDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 配送快递名字
     */
    private String companyName;
    /**
     * 类型
     */
    private String type;
    /**
     * 配送信息
     */
    private String info;
    /**
     * 配送编码(快递鸟)
     */
    private String kdnCode;
    /**
     * 信息模板
     */
    private String template;
    /**
     * 快递配置信息
     */
    private String config;

}
