package com.dofast.module.wms.api.StorageLocationApi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StorageLocationDTO {
    private Long id;

    private String locationCode;

    private String locationName;

    private Long warehouseId;

    private BigDecimal area;

    private String areaFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;
}
