package com.dofast.module.wms.api.WarehosueApi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WarehouseDTO {
    private Long id;

    private String warehouseCode;

    private String warehouseName;

    private String location;

    private BigDecimal area;

    private String charge;

    private String enableFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;

}
