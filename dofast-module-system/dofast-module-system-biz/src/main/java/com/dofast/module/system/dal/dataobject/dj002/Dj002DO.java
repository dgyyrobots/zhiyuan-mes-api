package com.dofast.module.system.dal.dataobject.dj002;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统地址信息 DO
 *
 * @author 惠智造
 */
@TableName("t_dj002")
@KeySequence("t_dj002_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dj002DO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 公司名称
     */
    private String deptName;
    /**
     * 系统名称
     */
    private String sysName;
    /**
     * 内网系统访问地址
     */
    private String sysUrl;
    /**
     * 外网系统访问地址
     */
    private String sysUrlNet;
    /**
     * 图标地址
     */
    private String sysLogUrl;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 修改者
     */
    private String updateUser;
    /**
     * 程序编号
     */
    private String pg;

}
