package com.revature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final Calculator calc = new Calculator();

    @Test
    void test_add() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void test_subtract() {
        assertEquals(-1, calc.subtract(2, 3));
    }

    @Test
    void test_exp() {
        assertEquals(8, calc.exp(2, 3));
    }

    @Test
    void test_div() {
        assertEquals(5, calc.div(10, 2));
    }

    @Test
    void test_mul() {
        assertEquals(20, calc.mul(10, 2));
    }
}
