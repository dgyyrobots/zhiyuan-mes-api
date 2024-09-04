package com.dofast.module.system.api.auth.dto;

import cn.hutool.http.HttpConfig;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class AuthConfigDTO {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String alipayPublicKey;
    private boolean unionId;
    private String stackOverflowKey;
    private String agentId;
    private String domainPrefix;
    private HttpConfig httpConfig;
    private boolean ignoreCheckState;
    private List<String> scopes;
    private String deviceId;
    private Integer clientOsType;
    private String packId;
    private boolean pkce;
    private String authServerId;
    private boolean ignoreCheckRedirectUri;
}
