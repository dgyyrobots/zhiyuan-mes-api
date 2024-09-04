package com.dofast.module.pro.controller.admin.routeproduct.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RouteProductListVO {
    private Long id;

    private Long routeId;

    private Long itemId;

    private String itemCode;

    private String itemName;

    private String specification;

    private String unitOfMeasure;

    private Integer quantity;

    private BigDecimal productionTime;

    private String timeUnitType;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;
}
