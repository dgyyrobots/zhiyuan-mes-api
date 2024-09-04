package com.dofast.module.channel.api.remark.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RemarkDto {

    /**
     * 自增ID
     */
    private Long id;
    /**
     * 关联的商城订单的ID
     */
    private Long tradeOrderId;
    /**
     * 排序权重
     */
    private Integer sortCode = 0;
    /**
     * 备注
     */
    private String remark;
    /**
     * 种类
     */
    private String type;
    /**
     * 销售订单ID
     */
    private Long salId;
}
