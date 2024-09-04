package com.dofast.module.infra.dal.dataobject.diyviewutil;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 自定义模板组件 DO
 *
 * @author 惠智造
 */
@TableName("diy_view_util")
@KeySequence("diy_view_util_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyViewUtilDO extends BaseDO {

    /**
     * 商城模板组件id
     */
    @TableId
    private Integer id;
    /**
     * 标识
     */
    private String name;
    /**
     * 组件名称
     */
    private String title;
    /**
     * 组件类型
     */
    private String type;
    /**
     * 配置:json格式
     */
    private String value;
    /**
     * 插件标识
     */
    private String addonName;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 支持的自定义页面（为空表示公共组件都支持）
     */
    private String supportDiyView;
    /**
     * 限制添加次数
     */
    private Integer maxCount;
    /**
     * 是否可以删除，0 允许，1 禁用
     */
    private Integer isDelete;
    /**
     * 组件图标
     */
    private String icon;

}
