package com.dofast.module.report.dal.dataobject.goview;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * GoView登录 DO
 *
 * @author 惠智造
 */
@TableName("report_go_view_code")
@KeySequence("report_go_view_code_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoViewCodeDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 随机码
     */
    private String code;
    /**
     * 状态(0未使用 1已扫码 2已确认)
     */
    private Long status;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    /**
     * PDA用户ID
     */
    private String pdaUserId;
    /**
     * PDA原始token
     */
    private String pdaToken;
    /**
     * 生成的goview token
     */
    private String goviewToken;

}
