package com.dofast.framework.social.core;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ReflectUtil;
import com.dofast.framework.common.util.servlet.ServletUtils;
import com.dofast.framework.social.core.enums.AuthExtendSource;
import com.dofast.framework.social.core.request.AuthWeChatMiniAppRequest;/*
import com.dofast.module.system.api.auth.AuthConfigApi;
import com.dofast.module.system.api.auth.dto.AuthConfigDTO;*/
import com.dofast.module.system.api.auth.AuthConfigApi;
import com.dofast.module.system.api.auth.dto.AuthConfigDTO;
import com.xingyuv.jushauth.config.AuthSource;
import com.xkcoding.justauth.AuthRequestFactory;
import com.xkcoding.justauth.autoconfigure.JustAuthProperties;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 第三方授权拓展 request 工厂类
 * 为使得拓展配置 {@link AuthConfig} 和默认配置齐平，所以自定义本工厂类
 *
 * @author timfruit
 * @date 2021-10-31
 */
@Component
public class DofastAuthRequestFactory extends AuthRequestFactory {
    @Resource
    AuthConfigApi authConfigApi;

    protected JustAuthProperties properties;
    protected AuthStateCache authStateCache;

    /**
     * 由于父类 configureHttpConfig 方法是 private 修饰，所以获取后，进行反射调用
     */
    private final Method configureHttpConfigMethod = ReflectUtil.getMethod(AuthRequestFactory.class,
            "configureHttpConfig", String.class, AuthConfig.class, JustAuthProperties.JustAuthHttpConfig.class);

    public DofastAuthRequestFactory(JustAuthProperties properties, AuthStateCache authStateCache) {
        super(properties, authStateCache);
        this.properties = properties;
        this.authStateCache = authStateCache;
    }

    /**
     * 返回 AuthRequest 对象
     *
     * @param source {@link AuthSource}
     * @return {@link com.xingyuv.jushauth.request.AuthRequest}
     */
    @Override
    public AuthRequest get(String source) {
        // 先尝试获取自定义扩展的
        AuthRequest authRequest = getExtendRequest(source);
        // 找不到，使用默认拓展
        if (authRequest == null) {
            authRequest = super.get(source);
        }
        return authRequest;
    }

    protected AuthRequest getExtendRequest(String source) {
        AuthExtendSource authExtendSource;
        try {
            authExtendSource = EnumUtil.fromString(AuthExtendSource.class, source.toUpperCase());
        } catch (IllegalArgumentException e) {
            // 无自定义匹配
            return null;
        }
        // 拓展配置和默认配置齐平，properties 放在一起
        AuthConfig config = properties.getType().get(authExtendSource.name());
        // 找不到对应关系，直接返回空
        if (config == null) {
            return null;
        }
        /*AuthConfigDTO authConfigDTO = authConfigApi.getAuthConfig(source);
        // 找不到对应关系，直接返回空
        if (authConfigDTO == null) {
            return null;
        }
        AuthConfig config = BeanUtil.toBean(authConfigDTO, AuthConfig.class);
        properties.getType().put(authExtendSource.name(), config);*/
        // 反射调用，配置 http config
        ReflectUtil.invoke(this, configureHttpConfigMethod, authExtendSource.name(), config, properties.getHttpConfig());

        // 获得拓展的 Request
        // noinspection SwitchStatementWithTooFewBranches
        switch (authExtendSource) {
            case WECHAT_MINI_APP:
                return new AuthWeChatMiniAppRequest(config, authStateCache);
            default:
                return null;
        }
    }

}

