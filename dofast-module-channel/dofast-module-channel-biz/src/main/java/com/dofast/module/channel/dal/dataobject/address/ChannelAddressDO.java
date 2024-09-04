package com.dofast.module.channel.dal.dataobject.address;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
// import com.sun.xml.internal.bind.v2.TODO;
import lombok.*;

/**
 * 交易客户 DO
 *
 * @author 芋道源码
 */
@TableName("channel_address")
@KeySequence("channel_address_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelAddressDO extends BaseDO {

    /**
     * 订单ID
     */
    @TableId
    private Integer id;
    /**
     * 相关单ID
     */
    private String refOid;
    /**
     * 店铺编码
     */
    private String posCode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 渠道
     *
     * 枚举 {@link TODO order_from_channel 对应的类}
     */
    private String refType;
    /**
     * 收货人国家
     */
    private String receiverCountry;
    /**
     * 收货人省
     */
    private String receiverState;
    /**
     * 收货人市
     */
    private String receiverCity;
    /**
     * 收件人区/县
     */
    private String receiverDistrict;
    /**
     * 收货人镇
     */
    private String receiverTown;
    /**
     * 收货人ID字段，可用于区分多个订单是否属于同一个收货人
     */
    private String receiverId;
    /**
     * 买家昵称
     */
    private String openBuyerNick;
    /**
     * 买家昵称
     */
    private String openBuyerId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联收货地址ID
     */
    private Integer addressId;
    /**
     * 关联用户ID
     */
    private Integer userId;

}
