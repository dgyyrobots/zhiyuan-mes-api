package com.dofast.module.infra.dal.dataobject.diytheme;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 自定义模板主题风格配色 DO
 *
 * @author 惠智造
 */
@TableName("diy_theme")
@KeySequence("diy_theme_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyThemeDO extends BaseDO {

    /**
     * 商城主题id
     */
    @TableId
    private Integer id;
    /**
     * 名称
     */
    private String title;
    /**
     * 标识
     */
    private String name;
    /**
     * 插件标识
     */
    private String addonName;
    /**
     * 主色调
     */
    private String mainColor;
    /**
     * 辅色调
     */
    private String auxColor;
    /**
     * 预览图，多个逗号隔开
     */
    private String preview;
    /**
     * 配色图片
     */
    private String colorImg;
    /**
     * 其他配色
     */
    private String value;

}
