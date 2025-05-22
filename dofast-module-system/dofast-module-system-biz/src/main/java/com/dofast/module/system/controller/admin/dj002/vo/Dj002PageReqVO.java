package com.dofast.module.system.controller.admin.dj002.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统地址信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Dj002PageReqVO extends PageParam {

    @Schema(description = "公司名称", example = "赵六")
    private String deptName;

    @Schema(description = "系统名称", example = "李四")
    private String sysName;

    @Schema(description = "内网系统访问地址", example = "https://www.iocoder.cn")
    private String sysUrl;

    @Schema(description = "外网系统访问地址")
    private String sysUrlNet;

    @Schema(description = "图标地址", example = "https://www.iocoder.cn")
    private String sysLogUrl;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] createTime;

    @Schema(description = "创建者")
    private String createUser;

    @Schema(description = "修改者")
    private String updateUser;

    @Schema(description = "程序编号")
    private String pg;

}
