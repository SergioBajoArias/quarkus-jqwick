package com.sergio.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ApplicationScoped
public class DivideService {
    public BigDecimal divide(int dividend, int divisor) {
        return BigDecimal.valueOf(dividend).divide(BigDecimal.valueOf(divisor), 5, RoundingMode.HALF_UP);
    }
}
