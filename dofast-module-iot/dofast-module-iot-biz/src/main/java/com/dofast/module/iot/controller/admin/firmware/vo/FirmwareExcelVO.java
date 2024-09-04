package com.dofast.module.iot.controller.admin.firmware.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品固件 Excel VO
 *
 * @author 惠智造
 */
@Data
public class FirmwareExcelVO {

    @ExcelProperty("固件ID")
    private Long id;

    @ExcelProperty("固件名称")
    private String firmwareName;

    @ExcelProperty("产品ID")
    private Long productId;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("是否系统通用（0-否，1-是）")
    private Boolean isSys;

    @ExcelProperty("是否最新版本（0-否，1-是）")
    private Boolean isLatest;

    @ExcelProperty("固件版本")
    private Double version;

    @ExcelProperty("文件路径")
    private String filePath;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
