package com.dofast.module.wms.dal.dataobject.barcode;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 条码清单 DO
 *
 * @author 芋道源码
 */
@TableName("wms_barcode")
@KeySequence("wms_barcode_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeDO extends BaseDO {

    /**
     * 条码ID
     */
    @TableId
    private Long id;
    /**
     * 条码格式
     */
    private String barcodeFormart;
    /**
     * 条码类型
     */
    private String barcodeType;
    /**
     * 条码内容
     */
    private String barcodeContent;
    /**
     * 业务ID
     */
    private Long bussinessId;
    /**
     * 业务编码
     */
    private String bussinessCode;
    /**
     * 业务名称
     */
    private String bussinessName;
    /**
     * 条码地址
     */
    private String barcodeUrl;
    /**
     * 是否生效
     */
    private String enableFlag;
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
