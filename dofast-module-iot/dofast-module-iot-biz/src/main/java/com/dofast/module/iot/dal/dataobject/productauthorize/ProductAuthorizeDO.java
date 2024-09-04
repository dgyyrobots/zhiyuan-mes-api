package com.dofast.module.iot.dal.dataobject.productauthorize;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品授权码 DO
 *
 * @author 惠智造
 */
@TableName("iot_product_authorize")
@KeySequence("iot_product_authorize_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAuthorizeDO extends BaseDO {

    /**
     * 授权码ID
     */
    @TableId
    private Long id;
    /**
     * 授权码
     */
    private String authorizeCode;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备编号
     */
    private String serialNumber;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 状态（1-未使用，2-使用中）
     */
    private Boolean status;
    /**
     * 备注
     */
    private String remark;

}
