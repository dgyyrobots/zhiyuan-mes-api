package com.dofast.module.cmms.framework;

import com.dofast.framework.swagger.config.DofastSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class CmmsWebConfiguration {
    /**
     * cmms 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi cmmsGroupedOpenApi() {
        return DofastSwaggerAutoConfiguration.buildGroupedOpenApi("mes/cmms");
    }
}
