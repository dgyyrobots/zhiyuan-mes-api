 
package com.dofast.framework.social.config;

import com.dofast.framework.social.core.DofastAuthRequestFactory;
import com.xkcoding.http.HttpUtil;
import com.xkcoding.http.support.hutool.HutoolImpl;
import com.xkcoding.justauth.autoconfigure.JustAuthProperties;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 社交自动装配类
 *
 * @author timfruit
 * @date 2021-10-30
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(JustAuthProperties.class)
public class DofastSocialAutoConfiguration {

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "justauth", value = "enabled", havingValue = "true", matchIfMissing = true)
    public DofastAuthRequestFactory dofastAuthRequestFactory(JustAuthProperties properties, AuthStateCache authStateCache) {
        // 需要修改 HttpUtil 使用的实现，避免类报错
        HttpUtil.setHttp(new HutoolImpl());
        // 创建 DofastAuthRequestFactory
        return new DofastAuthRequestFactory(properties, authStateCache);
    }

}

