package com.dofast.module.sales.controller.admin.orderafter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 售后流程表单 Excel VO
 *
 * @author a1
 */
@Data
public class OrderAfterExcelVO {

    @ExcelProperty("售后表单主键")
    private Long id;

    @ExcelProperty("编码")
    private String code;

    @ExcelProperty("关联订单id")
    private String associatedBusiness;

    @ExcelProperty("业务数据")
    private String businessData;

    @ExcelProperty("关联对象")
    private String associatedObjects;

    @ExcelProperty("对象名称")
    private String objectName;

    @ExcelProperty("关联店铺")
    private String associatedStores;

    @ExcelProperty("关联仓库")
    private String associatedRepository;

    @ExcelProperty("关联金额")
    private Double associatedAmounts;

    @ExcelProperty("事务类别")
    private String transactionCategory;

    @ExcelProperty("优先级")
    private Integer priority;

    @ExcelProperty("添加抄送")
    private String addCopy;

    @ExcelProperty("主要负责人")
    private String mainPersonResponsible;

    @ExcelProperty("截止时间")
    private LocalDateTime deadline;

    @ExcelProperty("标记")
    private String remark;

    @ExcelProperty("事务标题")
    private String transactionTitle;

    @ExcelProperty("事务内容")
    private String transactionContent;

    @ExcelProperty("图片")
    private String picture;

    @ExcelProperty("售后结果")
    private Byte status;

    @ExcelProperty("流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
