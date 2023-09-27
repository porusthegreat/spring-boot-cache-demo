package com.porus.CacheDemo.services;

import com.porus.CacheDemo.models.Operator;
import com.porus.CacheDemo.models.ResultHolder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService extends AbstractCacheableService{


    @Cacheable(value = "Factorials", keyGenerator = "resultKeyGenerator")
    public ResultHolder factorial(int number) {
        this.cacheMiss.set(true);
        simulateLatency();


        if (number <= 2) {
            return ResultHolder.of(number, Operator.FACTORIAL, number == 2 ? 2 : 1);
        }

        int operand = number;
        int result = number;

        while (number > 1) {
            result *= number;
            number--;
        }

        return ResultHolder.of(operand, Operator.FACTORIAL, result);
    }

    @Cacheable(value = "SquareRoots", keyGenerator = "resultKeyGenerator")
    public ResultHolder sqrt(int number) {
        this.cacheMiss.set(true);
        int result = Double.valueOf(Math.sqrt(number)).intValue();
        return ResultHolder.of(number, Operator.SQUARE_ROOT, result);
    }


}
