package com.dofast.module.infra.test;

import com.dofast.framework.datasource.config.DofastDataSourceAutoConfiguration;
import com.dofast.framework.mybatis.config.DofastMybatisAutoConfiguration;
import com.dofast.framework.redis.config.DofastRedisAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BaseDbAndRedisIntegrationTest.Application.class)
@ActiveProfiles("integration-test") // 设置使用 application-integration-test 配置文件
public class BaseDbAndRedisIntegrationTest {

    @Import({
            // DB 配置类
            DynamicDataSourceAutoConfiguration.class, // Dynamic Datasource 配置类
            DofastDataSourceAutoConfiguration.class, // 自己的 DB 配置类
            DataSourceAutoConfiguration.class, // Spring DB 自动配置类
            DataSourceTransactionManagerAutoConfiguration.class, // Spring 事务自动配置类
            // MyBatis 配置类
            DofastMybatisAutoConfiguration.class, // 自己的 MyBatis 配置类
            MybatisPlusAutoConfiguration.class, // MyBatis 的自动配置类

            // Redis 配置类
            RedisAutoConfiguration.class, // Spring Redis 自动配置类
            DofastRedisAutoConfiguration.class, // 自己的 Redis 配置类
            RedissonAutoConfiguration.class, // Redisson 自动高配置类
    })
    public static class Application {
    }

}
