package com.dofast.module.trade.controller.admin.calculate.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 计价记录 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CalculateRecordExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("数据")
    private Long recordId;

    @ExcelProperty("类型")
    private Long typeId;

    @ExcelProperty("结果")
    private String result;

    @ExcelProperty("操作系统")
    private String os;

    @ExcelProperty("客户端")
    private String client;

    @ExcelProperty("设备信息")
    private String device;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
