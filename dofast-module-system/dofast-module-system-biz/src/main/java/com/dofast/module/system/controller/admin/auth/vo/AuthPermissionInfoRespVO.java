package com.dofast.module.system.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Schema(description = "管理后台 - 登录用户的权限信息 Response VO,额外包括用户信息和角色列表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthPermissionInfoRespVO {

    @Schema(description = "用户信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserVO user;

    @Schema(description = "角色标识数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<String> roles;

    @Schema(description = "操作权限数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<String> permissions;

    @Schema(description = "操作菜单数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<AuthMenuRespVO> menus;

    @Schema(description = "用户信息 VO")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserVO {

        @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
        private Long id;

        @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道源码")
        private String nickname;

        @Schema(description = "租户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道源码")
        private String tenantname;

        @Schema(description = "用户头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://www.iocoder.cn/xx.jpg")
        private String avatar;

        @Schema(description = "租户头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://www.iocoder.cn/xx.jpg")
        private String logo;
    }

}
