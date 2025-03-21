package com.dofast.module.report.controller.admin.goview.vo.code;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * GoView登录 Excel VO
 *
 * @author 惠智造
 */
@Data
public class GoViewCodeExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("随机码")
    private String code;

    @ExcelProperty("状态(0未使用 1已扫码 2已确认)")
    private Long status;

    @ExcelProperty("过期时间")
    private LocalDateTime expireTime;

    @ExcelProperty("PDA用户ID")
    private String pdaUserId;

    @ExcelProperty("PDA原始token")
    private String pdaToken;

    @ExcelProperty("生成的goview token")
    private String goviewToken;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
