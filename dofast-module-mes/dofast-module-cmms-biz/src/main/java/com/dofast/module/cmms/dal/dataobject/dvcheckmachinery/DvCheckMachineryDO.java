package com.dofast.module.cmms.dal.dataobject.dvcheckmachinery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 点检设备 DO
 *
 * @author 芋道源码
 */
@TableName("cmms_dv_check_machinery")
@KeySequence("cmms_dv_check_machinery_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvCheckMachineryDO extends BaseDO {

    /**
     * 流水号
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private Long planId;
    /**
     * 设备ID
     */
    private Long machineryId;
    /**
     * 设备编码
     */
    private String machineryCode;
    /**
     * 设备名称
     */
    private String machineryName;
    /**
     * 品牌
     */
    private String machineryBrand;
    /**
     * 规格型号
     */
    private String machinerySpec;
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

    /**
     * ERP设备编码
     */
    private String erpMachineryCode;

}
