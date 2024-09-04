package com.dofast.module.infra.dal.dataobject.diytemplategoodsitem;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 模板组页面 DO
 *
 * @author 惠智造
 */
@TableName("diy_template_goods_item")
@KeySequence("diy_template_goods_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyTemplateGoodsItemDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer goodsItemId;
    /**
     * 模板组id
     */
    private Integer goodsId;
    /**
     * 名称
     */
    private String title;
    /**
     * 所属页面（首页、分类，空为微页面）
     */
    private String name;
    /**
     * 插件标识
     */
    private String addonName;
    /**
     * 模板数据
     */
    private String value;

}
