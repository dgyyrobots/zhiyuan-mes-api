package com.dofast.module.channel.api.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShopBaseDTO {

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
