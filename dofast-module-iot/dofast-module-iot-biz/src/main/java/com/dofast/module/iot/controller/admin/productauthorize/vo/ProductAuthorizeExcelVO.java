package com.dofast.module.iot.controller.admin.productauthorize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品授权码 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ProductAuthorizeExcelVO {

    @ExcelProperty("授权码ID")
    private Long id;

    @ExcelProperty("授权码")
    private String authorizeCode;

    @ExcelProperty("产品ID")
    private Long productId;

    @ExcelProperty("设备ID")
    private Long deviceId;

    @ExcelProperty("设备编号")
    private String serialNumber;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("状态（1-未使用，2-使用中）")
    private Boolean status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
