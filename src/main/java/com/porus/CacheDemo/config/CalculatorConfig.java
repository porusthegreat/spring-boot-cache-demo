package com.porus.CacheDemo.config;

import com.porus.CacheDemo.models.Operator;
import com.porus.CacheDemo.models.ResultHolder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = ResultHolder.class)
public class CalculatorConfig {

    @Bean
    CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("Factorials", "SquareRoots");
    }

    @Bean
    KeyGenerator resultKeyGenerator() {
        return (target, method, params) -> {
            int operand = Integer.parseInt(String.valueOf(params[0]));
            Operator operator = "sqrt".equals(method.getName())
                    ? Operator.SQUARE_ROOT
                    : Operator.FACTORIAL;

            return ResultHolder.ResultKey.of(operand, operator);
        };
    }
}

