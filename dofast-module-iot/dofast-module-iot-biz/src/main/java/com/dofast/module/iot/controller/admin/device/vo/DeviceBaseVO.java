package com.dofast.module.iot.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 设备 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DeviceBaseVO {

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3658")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25972")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "用户昵称不能为空")
    private String userName;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备编号不能为空")
    private String serialNumber;

    @Schema(description = "固件版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "固件版本不能为空")
    private Double firmwareVersion;

    @Schema(description = "设备状态（1-未激活，2-禁用，3-在线，4-离线）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "设备状态（1-未激活，2-禁用，3-在线，4-离线）不能为空")
    private Boolean status;

    @Schema(description = "信号强度（	信号极好4格[-55— 0]，	信号好3格[-70— -55]，	信号一般2格[-85— -70]，	信号差1格[-100— -85]）")
    private Byte rssi;

    @Schema(description = "是否启用设备影子(0=禁用，1=启用)")
    private Boolean isShadow;

    @Schema(description = "定位方式(1=ip自动定位，2=设备定位，3=自定义)")
    private Boolean locationWay;

    @Schema(description = "物模型值")
    private String thingsModelValue;

    @Schema(description = "设备所在地址")
    private String networkAddress;

    @Schema(description = "设备入网IP")
    private String networkIp;

    @Schema(description = "设备经度")
    private Object longitude;

    @Schema(description = "设备纬度")
    private Object latitude;

    @Schema(description = "激活时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime activeTime;

    //，格式[{"name":"device"},{"chip":"esp8266"}]
    @Schema(description = "设备摘要")
    private String summary;

    @Schema(description = "图片地址", example = "https://www.iocoder.cn")
    private String imgUrl;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
