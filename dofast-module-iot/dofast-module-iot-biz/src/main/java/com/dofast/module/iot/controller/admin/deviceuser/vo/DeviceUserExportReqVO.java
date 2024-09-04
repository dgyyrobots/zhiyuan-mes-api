package com.dofast.module.iot.controller.admin.deviceuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备用户 Excel 导出 Request VO，参数和 DeviceUserPageReqVO 是一致的")
@Data
public class DeviceUserExportReqVO {

    @Schema(description = "设备名称", example = "赵六")
    private String deviceName;

    @Schema(description = "手机号码")
    private String phonenumber;

    @Schema(description = "用户昵称", example = "李四")
    private String userName;

    @Schema(description = "是否为设备所有者（0=否，1=是）")
    private Byte isOwner;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
