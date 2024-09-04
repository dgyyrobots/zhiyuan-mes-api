package com.dofast.module.system.api.auth;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.system.api.auth.dto.AuthConfigDTO;
import com.dofast.module.system.service.systemconfig.SystemConfigService;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthConfigApiImpl implements AuthConfigApi{
    @Resource
    SystemConfigService systemConfigService;

    @Override
    public AuthConfigDTO getAuthConfig(String configKey) {
        AuthConfig authConfig = systemConfigService.getAuthConfig(configKey);
        AuthConfigDTO authConfigDTO = BeanUtil.toBean(authConfig, AuthConfigDTO.class);
        return authConfigDTO;
    }
}
