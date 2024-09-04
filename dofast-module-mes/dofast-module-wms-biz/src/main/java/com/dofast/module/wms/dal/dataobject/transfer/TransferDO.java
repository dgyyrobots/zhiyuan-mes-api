package com.dofast.module.wms.dal.dataobject.transfer;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 转移单 DO
 *
 * @author 芋道源码
 */
@TableName("wms_transfer")
@KeySequence("wms_transfer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferDO extends BaseDO {

    /**
     * 转移单ID
     */
    @TableId
    private Long id;
    /**
     * 转移单编号
     */
    private String transferCode;
    /**
     * 转移单名称
     */
    private String transferName;
    /**
     * 转移单类型
     */
    private String transferType;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 承运商
     */
    private String carrier;
    /**
     * 托运单号
     */
    private String bookingNote;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 收货人名称
     */
    private String receiverNick;
    /**
     * 移出仓库ID
     */
    private Long fromWarehouseId;
    /**
     * 移出仓库编码
     */
    private String fromWarehouseCode;
    /**
     * 移出仓库名称
     */
    private String fromWarehouseName;
    /**
     * 移入仓库ID
     */
    private Long toWarehouseId;
    /**
     * 移入仓库编码
     */
    private String toWarehouseCode;
    /**
     * 移入仓库名称
     */
    private String toWarehouseName;
    /**
     * 转移日期
     */
    private LocalDateTime transferDate;
    /**
     * 单据状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预留字段1
     */
    private String attr1;
    /**
     * 预留字段2
     */
    private String attr2;
    /**
     * 预留字段3
     */
    private Integer attr3;
    /**
     * 预留字段4
     */
    private Integer attr4;

}
