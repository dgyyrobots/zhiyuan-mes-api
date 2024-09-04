package com.dofast.framework.tenant.config;

 
import cn.hutool.core.annotation.AnnotationUtil;
import com.dofast.framework.common.enums.WebFilterOrderEnum;
import com.dofast.framework.mybatis.core.util.MyBatisUtils;
import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.framework.redis.config.DofastCacheProperties;
import com.dofast.framework.tenant.core.aop.TenantIgnoreAspect;
import com.dofast.framework.tenant.core.db.TenantDatabaseInterceptor;
import com.dofast.framework.tenant.core.job.TenantJob;
import com.dofast.framework.tenant.core.job.TenantJobAspect;
import com.dofast.framework.tenant.core.job.TenantJobHandlerDecorator;
import com.dofast.framework.common.enums.WebFilterOrderEnum;
import com.dofast.framework.mybatis.core.util.MyBatisUtils;
import com.dofast.framework.redis.config.DofastCacheProperties;
import com.dofast.framework.tenant.core.aop.TenantIgnoreAspect;
import com.dofast.framework.tenant.core.db.TenantDatabaseInterceptor;
import com.dofast.framework.tenant.core.job.TenantJobAspect;

import com.dofast.framework.tenant.core.mq.TenantRedisMessageInterceptor;
import com.dofast.framework.tenant.core.redis.TenantRedisCacheManager;
import com.dofast.framework.tenant.core.security.TenantSecurityWebFilter;
import com.dofast.framework.tenant.core.service.TenantFrameworkService;
import com.dofast.framework.tenant.core.service.TenantFrameworkServiceImpl;
import com.dofast.framework.tenant.core.web.TenantContextWebFilter;
import com.dofast.framework.web.config.WebProperties;
import com.dofast.framework.web.core.handler.GlobalExceptionHandler;
import com.dofast.module.system.api.tenant.TenantApi;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
 
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.BatchStrategies;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@AutoConfiguration
@ConditionalOnProperty(prefix = "dofast.tenant", value = "enable", matchIfMissing = true) // 允许使用 dofast.tenant.enable=false 禁用多租户
@EnableConfigurationProperties(TenantProperties.class)
public class DofastTenantAutoConfiguration {

    @Bean
    public TenantFrameworkService tenantFrameworkService(TenantApi tenantApi) {
        return new TenantFrameworkServiceImpl(tenantApi);
    }

    // === AOP ===

    @Bean
    public TenantIgnoreAspect tenantIgnoreAspect() {
        return new TenantIgnoreAspect();
    }

    // === DB ===

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperties properties,
                                                                 MybatisPlusInterceptor interceptor) {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor(new TenantDatabaseInterceptor(properties));
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }

    // === WEB ===

    @Bean
    public FilterRegistrationBean<TenantContextWebFilter> tenantContextWebFilter() {
        FilterRegistrationBean<TenantContextWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantContextWebFilter());
        registrationBean.setOrder(WebFilterOrderEnum.TENANT_CONTEXT_FILTER);
        return registrationBean;
    }

    // === Security ===

    @Bean
    public FilterRegistrationBean<TenantSecurityWebFilter> tenantSecurityWebFilter(TenantProperties tenantProperties,
                                                                                   WebProperties webProperties,
                                                                                   GlobalExceptionHandler globalExceptionHandler,
                                                                                   TenantFrameworkService tenantFrameworkService) {
        FilterRegistrationBean<TenantSecurityWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantSecurityWebFilter(tenantProperties, webProperties,
                globalExceptionHandler, tenantFrameworkService));
        registrationBean.setOrder(WebFilterOrderEnum.TENANT_SECURITY_FILTER);
        return registrationBean;
    }

    // === MQ ===

    @Bean
    public TenantRedisMessageInterceptor tenantRedisMessageInterceptor() {
        return new TenantRedisMessageInterceptor();
    }

    // === Job ===

    @Bean
    public TenantJobAspect tenantJobAspect(TenantFrameworkService tenantFrameworkService) {
        return new TenantJobAspect(tenantFrameworkService);
    }

    // === Redis ===

    @Bean
    @Primary // 引入租户时，tenantRedisCacheManager 为主 Bean
    public RedisCacheManager tenantRedisCacheManager(RedisTemplate<String, Object> redisTemplate,
                                                     RedisCacheConfiguration redisCacheConfiguration,
                                                     DofastCacheProperties dofastCacheProperties) {
        // 创建 RedisCacheWriter 对象
        RedisConnectionFactory connectionFactory = Objects.requireNonNull(redisTemplate.getConnectionFactory());
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory,
                BatchStrategies.scan(dofastCacheProperties.getRedisScanBatchSize()));
        // 创建 TenantRedisCacheManager 对象
        return new TenantRedisCacheManager(cacheWriter, redisCacheConfiguration);
    }






}
