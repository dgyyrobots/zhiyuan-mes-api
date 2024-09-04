package com.dofast.module.trade.dal.dataobject.electronicsheetpackage;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * 电子面单 DO
 *
 * @author 惠智造
 */
@TableName("trade_electronicsheet_package")
@KeySequence("trade_electronicsheet_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicsheetPackageDO extends BaseDO {

    /**
     * 自增编码id
     */
    @TableId
    private Integer id;
    /**
     * 订单编码outerCode
     */
    private String orderNo;
    /**
     * 面单号
     */
    private String waybillCode;
    /**
     * 父面单号
     */
    private String parentWaybillCode;
    /**
     * 状态（0正常 -1不使用）
     */
    private Byte status;
    /**
     * 电子面单模板
     */
    private String printTemplate;

}
