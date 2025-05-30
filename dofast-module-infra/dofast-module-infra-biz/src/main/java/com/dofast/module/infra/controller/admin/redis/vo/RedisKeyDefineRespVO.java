package com.dofast.module.infra.controller.admin.redis.vo;

import com.dofast.framework.redis.core.RedisKeyDefine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Schema(description = "管理后台 - Redis Key 信息 Response VO")
@Data
@Builder
@AllArgsConstructor
public class RedisKeyDefineRespVO {

    @Schema(description = "Key 模板", requiredMode = Schema.RequiredMode.REQUIRED, example = "login_user:%s")
    private String keyTemplate;

    @Schema(description = "Key 类型的枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "String")
    private RedisKeyDefine.KeyTypeEnum keyType;

    @Schema(description = "Value 类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "java.lang.String")
    private Class<?> valueType;

    @Schema(description = "超时类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private RedisKeyDefine.TimeoutTypeEnum timeoutType;

    @Schema(description = "过期时间，单位：毫秒", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Duration timeout;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "啦啦啦啦~")
    private String memo;

}
