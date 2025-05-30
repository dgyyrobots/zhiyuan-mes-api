package com.dofast.module.wms.dal.dataobject.rtsalse;

import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RtSalseTxBean extends BaseDO {
    /** 产品物料ID */
    private Long itemId;

    /** 产品物料编码 */
    private String itemCode;

    /** 产品物料名称 */
    private String itemName;

    /** 规格型号 */
    private String specification;

    /** 单位 */
    private String unitOfMeasure;

    /** 入库批次号 */
    private String batchCode;

    /** 仓库ID */
    private Long warehouseId;

    /** 仓库编码 */
    private String warehouseCode;

    /** 仓库名称 */
    private String warehouseName;

    /** 库区ID */
    private Long locationId;

    /** 库区编码 */
    private String locationCode;

    /** 库区名称 */
    private String locationName;

    /** 库位ID */
    private Long areaId;

    /** 库位编码 */
    private String areaCode;

    /** 库位名称 */
    private String areaName;

    /** 供应商ID */
    private Long clientId;

    /** 客户编号 */
    private String clientCode;

    /** 客户名称 */
    private String clientName;

    /** 客户简称 */
    private String clientNick;

    /** 单据类型 */
    private String sourceDocType;

    /** 单据ID */
    private Long sourceDocId;

    /** 单据编号 */
    private String sourceDocCode;

    /** 单据行ID */
    private Long sourceDocLineId;

    /** 事务数量 */
    private BigDecimal transactionQuantity;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date rtDate;
}
