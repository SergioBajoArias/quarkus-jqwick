package com.sergio.service;

import net.jqwik.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideServiceTest {

    DivideService divideService = new DivideService();
    @Property(tries = 10, shrinking = ShrinkingMode.OFF, generation = GenerationMode.RANDOMIZED)
    public void testDivideBySelf(@ForAll("nonZeroNumbers") int dividend) {
        assertEquals(BigDecimal.ONE.setScale(5, RoundingMode.HALF_UP), divideService.divide(dividend, dividend));
    }

    @Provide
    Arbitrary<Integer> nonZeroNumbers() {
        return Arbitraries.integers().filter(v -> v != 0);
    }
}