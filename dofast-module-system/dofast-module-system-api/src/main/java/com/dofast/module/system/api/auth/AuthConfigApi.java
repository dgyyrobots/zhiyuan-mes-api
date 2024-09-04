package com.dofast.module.system.api.auth;

import com.dofast.module.system.api.auth.dto.AuthConfigDTO;

public interface AuthConfigApi {
    AuthConfigDTO getAuthConfig(String configKey);
}
