package com.dofast.module.iot.dal.dataobject.product;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品 DO
 *
 * @author 惠智造
 */
@TableName("iot_product")
@KeySequence("iot_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDO extends BaseDO {

    /**
     * 产品ID
     */
    @TableId
    private Long id;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品分类ID
     */
    private Long categoryId;
    /**
     * 产品分类名称
     */
    private String categoryName;
    /**
     * 是否系统通用（0-否，1-是）
     */
    private Boolean isSys;
    /**
     * 是否启用授权码（0-否，1-是）
     */
    private Boolean isAuthorize;
    /**
     * mqtt账号
     */
    private String mqttAccount;
    /**
     * mqtt密码
     */
    private String mqttPassword;
    /**
     * 产品秘钥
     */
    private String mqttSecret;
    /**
     * 状态（1-未发布，2-已发布）
     */
    private Boolean status;
    /**
     * 物模型JSON（属性、功能、事件）
     */
    private String thingsModelsJson;
    /**
     * 设备类型（1-直连设备、2-网关子设备、3-网关设备）
     */
    private Boolean deviceType;
    /**
     * 联网方式（1=wifi、2=蜂窝(2G/3G/4G/5G)、3=以太网、4=其他）
     */
    private Boolean networkMethod;
    /**
     * 认证方式（1-简单认证、2-加密认证、3-简单+加密）
     */
    private Boolean vertificateMethod;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 备注
     */
    private String remark;

}
