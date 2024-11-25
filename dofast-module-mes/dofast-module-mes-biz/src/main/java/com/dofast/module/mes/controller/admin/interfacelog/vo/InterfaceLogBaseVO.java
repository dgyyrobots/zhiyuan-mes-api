package com.dofast.module.mes.controller.admin.interfacelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 接口操作日志 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class InterfaceLogBaseVO {

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
