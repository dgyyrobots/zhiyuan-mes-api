package com.dofast.module.iot.controller.admin.deviceuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备用户 Excel VO
 *
 * @author 惠智造
 */
@Data
public class DeviceUserExcelVO {

    @ExcelProperty("设备ID")
    private Long id;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("设备名称")
    private String deviceName;

    @ExcelProperty("手机号码")
    private String phonenumber;

    @ExcelProperty("用户昵称")
    private String userName;

    @ExcelProperty("是否为设备所有者（0=否，1=是）")
    private Byte isOwner;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
