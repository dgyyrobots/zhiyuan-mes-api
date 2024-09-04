package com.dofast.module.system.controller.admin.wechat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Schema(description = "获取微信Token的返回信息")
public class WechatAccessTokenResponse {
    private int errcode;
    private String errmsg;
    private String access_token;
}
