package com.dofast.module.system.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 一键登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OneClickLoginReqVO {

    @Schema(description = "一键登录登录令牌",  example = "dofastyuanma")
    @NotBlank(message = "登录令牌不能为空")
    private String access_token;

    @Schema(description = "一键登录登录openId",  example = "dofastyuanma")
    @NotBlank(message = "openId不能为空")
    private String openid;

    @Schema(description = "app客户端",  example = "dofastyuanma")
    @NotBlank(message = "appid不能为空")
    private String appid;
    
    
    
    
    
    

}
