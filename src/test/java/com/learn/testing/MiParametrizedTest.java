package com.learn.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MiParametrizedTest {

    /*
    * We need to add  <artifactId>junit-jupiter-params</artifactId> dependecy for @ParameterizedTest and @CsvSource
    * */

    @DisplayName("Test with parameters")
    @ParameterizedTest(name = "uno {0} + dos {1} = resultado {2}")
    @CsvSource(value = {
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    public void testWithParameters(int first, int second, int expectedResult) {
        MiClase miClase = new MiClase();
        assertEquals(expectedResult, miClase.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);

        assertEquals(expectedResult, miClase.add(first, second),
                new Supplier<String>() {
                    @Override
                    public String get() {
                        return first + " + " + second + " should equal " + expectedResult;
                    }
                });
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }
}