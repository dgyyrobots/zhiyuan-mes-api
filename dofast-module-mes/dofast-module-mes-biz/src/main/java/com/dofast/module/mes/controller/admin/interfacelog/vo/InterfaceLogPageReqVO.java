package com.dofast.module.mes.controller.admin.interfacelog.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 接口操作日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InterfaceLogPageReqVO extends PageParam {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "接口名称", example = "赵六")
    private String interfaceName;

    @Schema(description = "发起方")
    private String requester;

    @Schema(description = "接收方")
    private String receiver;

    @Schema(description = "请求类型(POST/GET/PUT)", example = "1")
    private String requestType;

    @Schema(description = "请求报文")
    private String requestMap;

    @Schema(description = "接收报文")
    private String resultMap;

}
