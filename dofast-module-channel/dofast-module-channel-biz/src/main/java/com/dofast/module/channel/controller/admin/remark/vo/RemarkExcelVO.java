package com.dofast.module.channel.controller.admin.remark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单备注 Excel VO
 *
 * @author 惠智造
 */
@Data
public class RemarkExcelVO {

    @ExcelProperty("自增ID")
    private Long id;

    @ExcelProperty("关联的商城订单的ID")
    private Long tradeOrderId;

    @ExcelProperty("排序权重")
    private Integer sortCode;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("种类")
    private String type;

    @ExcelProperty("销售订单ID")
    private Long salId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
