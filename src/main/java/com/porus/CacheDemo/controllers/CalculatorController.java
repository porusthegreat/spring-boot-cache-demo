package com.porus.CacheDemo.controllers;

import com.porus.CacheDemo.models.ResultHolder;
import com.porus.CacheDemo.services.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("api/calc")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/factorial/{number}")
    public String factorial(@PathVariable("number") int number) {

        long t0 = System.currentTimeMillis();

        ResultHolder result = this.calculatorService.factorial(number);

        return toJson(result, System.currentTimeMillis() - t0, this.calculatorService.isCacheMiss());
    }

    private String toJson(ResultHolder result, long latency, boolean cacheMiss) {
        return format(format("{ math: %s, latency: %d ms, cacheMiss: %s }", result, latency, cacheMiss));
    }

}
