package com.dofast.module.promotion.dal.dataobject.diy;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.type.StringListTypeHandler;
import lombok.*;

import java.util.List;

/**
 * 装修页面 DO
 *
 * @author owen
 */
@TableName(value = "promotion_diy_page", autoResultMap = true)
@KeySequence("promotion_diy_page_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiyPageDO extends BaseDO {

    /**
     * 装修页面编号
     */
    @TableId
    private Long id;
    /**
     * 装修模板编号
     */
    private Long templateId;
    /**
     * 页面名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预览图，多个逗号分隔
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> previewImageUrls;
    /**
     * 页面属性，JSON 格式
     */
    private String property;

}
