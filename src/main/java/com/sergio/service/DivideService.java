package com.sergio.service;

import com.sergio.common.Constants;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ApplicationScoped
public class DivideService {
    public BigDecimal divide(int dividend, int divisor) {
        return BigDecimal.valueOf(dividend).divide(BigDecimal.valueOf(divisor), Constants.DEFAULT_NUMBER_OF_DECIMALS, Constants.DEFAULT_ROUNDING_MODE);
    }
}
