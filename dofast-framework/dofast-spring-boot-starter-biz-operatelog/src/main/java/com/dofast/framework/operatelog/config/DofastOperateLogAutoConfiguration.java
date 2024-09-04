package com.dofast.framework.operatelog.config;

import com.dofast.framework.operatelog.core.aop.OperateLogAspect;
import com.dofast.framework.operatelog.core.service.OperateLogFrameworkService;
import com.dofast.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.dofast.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class DofastOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
