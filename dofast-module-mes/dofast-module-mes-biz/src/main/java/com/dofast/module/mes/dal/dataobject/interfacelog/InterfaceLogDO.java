package com.dofast.module.mes.dal.dataobject.interfacelog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 接口操作日志 DO
 *
 * @author 惠智造
 */
@TableName("mes_interface_log")
@KeySequence("mes_interface_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceLogDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 发起方
     */
    private String requester;
    /**
     * 接收方
     */
    private String receiver;
    /**
     * 请求类型(POST/GET/PUT)
     */
    private String requestType;
    /**
     * 请求报文
     */
    private String requestMap;
    /**
     * 接收报文
     */
    private String resultMap;

}
