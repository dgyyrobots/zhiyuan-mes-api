package com.dofast.module.wms.api.StorageAreaApi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StorageAreaDTO {
    private Long id;

    private String areaCode;

    private String areaName;

    private Long locationId;

    private BigDecimal area;

    private BigDecimal maxLoa;

    private Integer positionX;

    private Integer positionY;

    private Integer positionZ;

    private String enableFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;
}
