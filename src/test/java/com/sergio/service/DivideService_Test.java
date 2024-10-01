package com.sergio.service;

import lombok.extern.slf4j.Slf4j;
import net.jqwik.api.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class DivideService_Test {

    DivideService divideService = new DivideService();
    @Property
    public void testDivideBySelf(@ForAll("nonZeroNumbers") int dividend) {
        assertEquals(0, BigDecimal.ONE.compareTo(divideService.divide(dividend, dividend)));
    }

    @Property
    public void testZeroDividedByAnyNumber(@ForAll("nonZeroNumbers") int divisor) {
        assertEquals(0, BigDecimal.ZERO.compareTo(divideService.divide(0, divisor)));
    }

    @Property
    public void testDivideByOne(@ForAll int dividend) {
        assertEquals(0, BigDecimal.valueOf(dividend).compareTo(divideService.divide(dividend, 1)));
    }

    @Property
    public void testDivideCheckResults(@ForAll int dividend, @ForAll("nonZeroNumbers") int divisor) {
        BigDecimal result = divideService.divide(dividend, divisor);
        BigDecimal difference = BigDecimal.valueOf(dividend).subtract(BigDecimal.valueOf(divisor).multiply(result));
        BigDecimal tolerance = BigDecimal.valueOf(0.01);
        assertTrue(List.of(-1, 0).contains(difference.compareTo(tolerance)));
    }

    @Property
    public void testDivideCheckResults(@ForAll int dividend1, @ForAll int dividend2, @ForAll("nonZeroNumbers") int constant) {
        BigDecimal result1 = divideService.divide(dividend1, constant);
        BigDecimal result2 = divideService.divide(dividend2, constant);
        if(constant > 0) {
            if(dividend1 > dividend2) {
                assertTrue(result1.compareTo(result2) > 0);
            } else if(dividend2 > dividend1) {
                assertTrue(result2.compareTo(result1) > 0);
            }
        } else {
            if(dividend1 > dividend2) {
                assertTrue(result1.compareTo(result2) < 0);
            } else if(dividend2 > dividend1) {
                assertTrue(result2.compareTo(result1) < 0);
            }
        }
    }



    @Provide
    Arbitrary<Integer> nonZeroNumbers() {
        return Arbitraries.integers().filter(v -> v != 0);
    }
}