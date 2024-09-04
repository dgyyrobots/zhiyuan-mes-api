package com.dofast.module.iot.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备 Excel VO
 *
 * @author 惠智造
 */
@Data
public class DeviceExcelVO {

    @ExcelProperty("设备ID")
    private Long id;

    @ExcelProperty("设备名称")
    private String deviceName;

    @ExcelProperty("产品ID")
    private Long productId;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户昵称")
    private String userName;

    @ExcelProperty("设备编号")
    private String serialNumber;

    @ExcelProperty("固件版本")
    private Double firmwareVersion;

    @ExcelProperty("设备状态（1-未激活，2-禁用，3-在线，4-离线）")
    private Boolean status;

    @ExcelProperty("信号强度（	信号极好4格[-55— 0]，	信号好3格[-70— -55]，	信号一般2格[-85— -70]，	信号差1格[-100— -85]）")
    private Byte rssi;

    @ExcelProperty("是否启用设备影子(0=禁用，1=启用)")
    private Boolean isShadow;

    @ExcelProperty("定位方式(1=ip自动定位，2=设备定位，3=自定义)")
    private Boolean locationWay;

    @ExcelProperty("物模型值")
    private String thingsModelValue;

    @ExcelProperty("设备所在地址")
    private String networkAddress;

    @ExcelProperty("设备入网IP")
    private String networkIp;

    @ExcelProperty("设备经度")
    private Object longitude;

    @ExcelProperty("设备纬度")
    private Object latitude;

    @ExcelProperty("激活时间")
    private LocalDateTime activeTime;

    //，格式[{"name":"device"},{"chip":"esp8266"}]
    @ExcelProperty("设备摘要")
    private String summary;

    @ExcelProperty("图片地址")
    private String imgUrl;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
