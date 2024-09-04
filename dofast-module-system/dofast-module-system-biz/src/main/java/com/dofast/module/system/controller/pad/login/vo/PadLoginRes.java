package com.dofast.module.system.controller.pad.login.vo;

import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Pad - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadLoginRes {
    @Schema(description = "用户信息", requiredMode = Schema.RequiredMode.REQUIRED, example = "happy")
    private PadLoginUser user;

    @Schema(description = "访问令牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "happy")
    private String token;

    @Schema(description = "刷新令牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "nice")
    private String refreshToken;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime expired;

    @Schema(description = "是否为代理人", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isAgent = true;


}
