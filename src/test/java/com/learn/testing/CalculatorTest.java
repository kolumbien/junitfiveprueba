package com.learn.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculatorTest {

    /*
    * We need to add  <artifactId>junit-jupiter-params</artifactId> dependecy for @ParameterizedTest and @CsvSource
    * */

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(value = {
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    public void testWithParameters(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);

        assertEquals(expectedResult, calculator.add(first, second),
                new Supplier<String>() {
                    @Override
                    public String get() {
                        return first + " + " + second + " should equal " + expectedResult;
                    }
                });
    }
}