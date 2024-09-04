package com.dofast.module.infra.dal.dataobject.diyviewtenant;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 租户自定义模板 DO
 *
 * @author 惠智造
 */
@TableName("diy_view_tenant")
@KeySequence("diy_view_tenant_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyViewTenantDO extends BaseDO {

    /**
     * 租户商城模板id
     */
    @TableId
    private Integer id;
    /**
     * 站点id
     */
    private Integer siteId;
    /**
     * 模板标识
     */
    private String name;
    /**
     * 模板名称
     */
    private String title;
    /**
     * 所属模板id
     */
    private Integer templateId;
    /**
     * 所属模板页面id，关联diy_template_goods_item表
     */
    private Integer templateItemId;
    /**
     * 页面类型
     */
    private String type;
    /**
     * 页面类型名称
     */
    private String typeName;
    /**
     * 是否默认页面（针对自定义模板设置），1：是，0：否
     */
    private Integer isDefault;
    /**
     * 插件标识
     */
    private String addonName;
    /**
     * 浏览量
     */
    private Integer clickNum;
    /**
     * 配置值
     */
    private String value;
    /**
     * 排序
     */
    private Integer sort;

}
