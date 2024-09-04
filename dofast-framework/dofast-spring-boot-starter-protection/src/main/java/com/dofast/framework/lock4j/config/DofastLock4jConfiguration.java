package com.dofast.framework.lock4j.config;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.lock.spring.boot.autoconfigure.LockAutoConfiguration;
import com.dofast.framework.lock4j.core.DefaultLockFailureStrategy;
import com.dofast.framework.lock4j.core.Lock4jRedisKeyConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(before = LockAutoConfiguration.class)
@ConditionalOnClass(name = "com.baomidou.lock.annotation.Lock4j")
public class DofastLock4jConfiguration {

    static {
        // 手动加载 Lock4jRedisKeyConstants 类，因为它不会被使用到
        // 如果不加载，会导致 Redis 监控，看到它的 Redis Key 枚举
        ClassUtil.loadClass(Lock4jRedisKeyConstants.class.getName());
    }


}
