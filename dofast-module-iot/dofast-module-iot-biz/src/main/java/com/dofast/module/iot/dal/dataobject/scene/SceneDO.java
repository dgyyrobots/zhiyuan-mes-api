package com.dofast.module.iot.dal.dataobject.scene;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场景联动 DO
 *
 * @author 惠智造
 */
@TableName("iot_scene")
@KeySequence("iot_scene_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneDO extends BaseDO {

    /**
     * 场景ID
     */
    @TableId
    private Long id;
    /**
     * 场景名称
     */
    private String sceneName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 触发器
     */
    private String triggers;
    /**
     * 执行动作
     */
    private String actions;
    /**
     * 备注
     */
    private String remark;

}
