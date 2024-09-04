package com.dofast.module.wms.controller.admin.transferline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Data
public class TransferLineListVO {
    private Long id;
    private Long transferId;

    private Long materialStockId;

    private Long itemId;

    private String itemCode;

    private String itemName;

    private String specification;

    private String unitOfMeasure;

    private Double quantityTransfer;

    private Long workorderId;

    private String workorderCode;

    private String batchCode;

    private Long fromWarehouseId;

    private String fromWarehouseCode;

    private String fromWarehouseName;

    private Long fromLocationId;

    private String fromLocationCode;

    private String fromLocationName;

    private Long fromAreaId;

    private String fromAreaCode;

    private String fromAreaName;

    private Long toWarehouseId;

    private String toWarehouseCode;

    private String toWarehouseName;

    private Long toLocationId;

    private String toLocationCode;

    private String toLocationName;

    private Long toAreaId;

    private String toAreaCode;

    private String toAreaName;

    private LocalDateTime expireDate;

    private Long vendorId;

    private String vendorCode;

    private String vendorName;

    private String vendorNick;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

}
