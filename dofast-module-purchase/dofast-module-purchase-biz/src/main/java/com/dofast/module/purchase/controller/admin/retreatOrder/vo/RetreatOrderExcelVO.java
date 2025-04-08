package com.dofast.module.purchase.controller.admin.retreatOrder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * ERP仓退单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class RetreatOrderExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("仓退单编号")
    private String retreatCode;

    @ExcelProperty("仓退单名称")
    private String retreatName;

    @ExcelProperty("供应商名称")
    private String vendorName;

    @ExcelProperty("供应商编号")
    private String vendorCode;

    @ExcelProperty("申请人员")
    private String retreatUser;

    @ExcelProperty("申请人员")
    private String retreatNick;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("仓退原因")
    private String retreatType;

    @ExcelProperty("状态")
    private String status;

}
