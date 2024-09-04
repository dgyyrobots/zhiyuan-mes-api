package com.dofast.module.wms.dal.dataobject.rtvendor;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 供应商退货 DO
 *
 * @author 芋道源码
 */
@TableName("wms_rt_vendor")
@KeySequence("wms_rt_vendor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtVendorDO extends BaseDO {

    /**
     * 退货单ID
     */
    @TableId
    private Long id;
    /**
     * 退货单编号
     */
    private String rtCode;
    /**
     * 退货单名称
     */
    private String rtName;
    /**
     * 采购订单编号
     */
    private String poCode;
    /**
     * 供应商ID
     */
    private Long vendorId;
    /**
     * 供应商编码
     */
    private String vendorCode;
    /**
     * 供应商名称
     */
    private String vendorName;
    /**
     * 供应商简称
     */
    private String vendorNick;
    /**
     * 批次号
     */
    private String batchCode;
    /**
     * 退货日期
     */
    private LocalDateTime rtDate;
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
