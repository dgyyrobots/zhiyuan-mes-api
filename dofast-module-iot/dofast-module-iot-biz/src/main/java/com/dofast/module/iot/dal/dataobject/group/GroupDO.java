package com.dofast.module.iot.dal.dataobject.group;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备分组 DO
 *
 * @author 惠智造
 */
@TableName("iot_group")
@KeySequence("iot_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

    /**
     * 分组ID
     */
    @TableId
    private Long id;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 分组排序
     */
    private Byte groupOrder;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 备注
     */
    private String remark;

}
