package com.dofast.module.trade.controller.admin.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 采集任务类型 Excel VO
 *
 * @author 惠智造
 */
@Data
public class OrderCollectionTypeExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("表单")
    private Long formId;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
