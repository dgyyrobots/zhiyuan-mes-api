package com.dofast.module.mes.framework;

import com.dofast.framework.swagger.config.DofastSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MesWebConfiguration {
    /**
     * mes 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi mesGroupedOpenApi() {
        return DofastSwaggerAutoConfiguration.buildGroupedOpenApi("mes");
    }
}
