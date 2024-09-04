package com.dofast.module.wms.dal.dataobject.barcodeconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 条码配置 DO
 *
 * @author 芋道源码
 */
@TableName("wms_barcode_config")
@KeySequence("wms_barcode_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeConfigDO extends BaseDO {

    /**
     * 配置ID
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
     * 内容格式
     */
    private String contentFormart;
    /**
     * 内容样例
     */
    private String contentExample;
    /**
     * 是否自动生成
     */
    private String autoGenFlag;
    /**
     * 默认的打印模板
     */
    private String defaultTemplate;
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
