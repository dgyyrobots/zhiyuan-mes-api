package com.dofast.module.mes.api.ProductBomApi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MdProductBomDTO {
    private Long id;

    private Long itemId;

    private Long bomItemId;

    private String bomItemCode;

    private String bomItemName;

    private String bomItemSpec;

    private String unitOfMeasure;

    private String itemOrProduct;

    private Double quantity;

    private String enableFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;
}
