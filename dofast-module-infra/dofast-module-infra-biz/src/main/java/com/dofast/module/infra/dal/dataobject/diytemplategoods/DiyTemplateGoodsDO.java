package com.dofast.module.infra.dal.dataobject.diytemplategoods;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 自定义模板组 DO
 *
 * @author 惠智造
 */
@TableName("diy_template_goods")
@KeySequence("diy_template_goods_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyTemplateGoodsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer goodsId;
    /**
     * 装修的页面项id，默认取第一个页面
     */
    private Integer goodsItemId;
    /**
     * 模板名称
     */
    private String title;
    /**
     * 模板标识
     */
    private String name;
    /**
     * 插件标识
     */
    private String addonName;
    /**
     * 封面图
     */
    private String cover;
    /**
     * 预览图
     */
    private String preview;
    /**
     * 模版描述
     */
    private String desc;
    /**
     * 模板分类id
     */
    private Integer categoryId;
    /**
     * 模板分类名称
     */
    private String categoryName;
    /**
     * 使用次数
     */
    private Integer useNum;

}
