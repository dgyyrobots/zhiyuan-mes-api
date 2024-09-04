package com.dofast.module.trade.controller.admin.electronicsheetpackage.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 电子面单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ElectronicsheetPackageExcelVO {

    @ExcelProperty("自增编码id")
    private Integer id;

    @ExcelProperty("订单编码outerCode")
    private String orderNo;

    @ExcelProperty("面单号")
    private String waybillCode;

    @ExcelProperty("父面单号")
    private String parentWaybillCode;

    @ExcelProperty("状态（0正常 -1不使用）")
    private Byte status;

    @ExcelProperty("电子面单模板")
    private String printTemplate;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
