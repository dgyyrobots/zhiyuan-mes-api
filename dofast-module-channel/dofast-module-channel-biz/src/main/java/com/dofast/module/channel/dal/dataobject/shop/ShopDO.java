package com.dofast.module.channel.dal.dataobject.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDateTime;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * 店铺授权 DO
 *
 * @author 芋道源码
 */
@TableName("channel_shop")
@KeySequence("channel_shop_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("ChannelShopDO")
public class ShopDO extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 店铺名称
     */
    private String displayName;
    /**
     * 店铺标识
     */
    private String name;
    /**
     * 掌柜昵称
     */
    private String shopNick;
    /**
     * 店铺地址
     */
    private String shopUrl;
    /**
     * 请求posId
     */
    private String shopId;
    /**
     * 请求posCode
     */
    private String shopCode;

    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 渠道
     *
     * 枚举 {@link TODO order_from_channel 对应的类}
     */
    private String channel;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序号
     */
    private Integer sortCode;
    /**
     * 上次拉去时间
     */
    private LocalDateTime lastTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 租户ID
     */
    private Integer tenantId;
}
