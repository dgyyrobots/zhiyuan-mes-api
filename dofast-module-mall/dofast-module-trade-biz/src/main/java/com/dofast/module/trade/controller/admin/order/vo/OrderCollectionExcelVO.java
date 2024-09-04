package com.dofast.module.trade.controller.admin.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 采集任务 Excel VO
 *
 * @author 惠智造
 */
@Data
public class OrderCollectionExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("任务类型")
    private Long typeId;

    @ExcelProperty("订单")
    private Long orderId;

    @ExcelProperty("记录")
    private Long recordId;

    @ExcelProperty("记录者")
    private Long recorder;

    @ExcelProperty("记录时间")
    private LocalDateTime recordTime;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
