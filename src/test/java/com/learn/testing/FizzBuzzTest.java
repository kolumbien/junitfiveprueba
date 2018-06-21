package com.learn.testing;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FizzBuzzTest {

    static FizzBuzz fizzBuzzObject;

    @BeforeAll
    public static void setUp(){
        fizzBuzzObject = new FizzBuzz();
        System.out.println("before all");
    }

    @Disabled
    @DisplayName("mi primer test en junit5")
    @Test
    public void testNumber(){
        System.out.println("test 1");
        String fizzBuzz = fizzBuzzObject.play(1);

        assertEquals(fizzBuzz, "1");
    }

    @DisplayName("fuzz 3")
    @Test
    public void testNumberThree(){
        System.out.println("test 2");
        String fizzBuzz = fizzBuzzObject.play(3);

        assertEquals(fizzBuzz, "Fizz");
    }

    @DisplayName("fuzz 5")
    @Test
    public void testNumberFive(){
        System.out.println("test 3");
        String fizzBuzz = fizzBuzzObject.play(5);

        assertEquals(fizzBuzz, "Buzz");
    }

    @DisplayName("fuzz 0")
    @Test
    public void testNumberZero(){
        System.out.println("test 0");

        assertThrows(IllegalArgumentException.class, () -> fizzBuzzObject.play(0));
    }

    @AfterEach
    public void tearDown(){
       // fizzBuzzObject = null;
    }
}