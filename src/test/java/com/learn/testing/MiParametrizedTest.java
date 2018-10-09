package com.learn.testing;

import com.learn.annotations.Adri;
import com.learn.utils.Book;
import com.learn.utils.PersonAggregator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;


class MiParametrizedTest {

    private boolean isPalindrome(String text) {
        return new StringBuilder(text).reverse().toString().equals(text);
    }

    /*
    * We need to add  <artifactId>junit-jupiter-params</artifactId> dependecy for @ParameterizedTest and @CsvSource
    * */
    @Disabled
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
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe",
            "John, Doe"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
        Person person = new Person(arguments.getString(0),
                arguments.getString(1));

        assertEquals("Doe", person.getLastName());

    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        System.out.println("**********palindrome**********");
        assertTrue(isPalindrome(candidate));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @Disabled
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }

    @Disabled
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    @Disabled
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = { "DAYS", "HOURS" })
    void testWithEnumSourceExclude(TimeUnit timeUnit) {
        System.out.println(timeUnit);
        assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        assertTrue(timeUnit.name().length() > 5);
    }

    @Disabled
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }

    /**
     *
     * @param argument Stream, Iterable, Iterator, or array of arguments
     */
    @Disabled
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithSimpleMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }

    @Disabled
    @ParameterizedTest
    @MethodSource
    void testWithSimpleMethodSourceHavingNoValue(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> testWithSimpleMethodSourceHavingNoValue() {
        return Stream.of("foo", "bar");
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("foo", 1, Arrays.asList("a", "b")),
                Arguments.of("bar", 2, Arrays.asList("x", "y"))
        );
    }


    @Disabled
    @ParameterizedTest
    @MethodSource("com.learn.utils.StringsProviders#names")
    void testWithExternalMethodSource(String name) {
        System.out.println(name);
        assertNotNull(name);
    }


    @Disabled
    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = "42 Cats")
    void testWithImplicitFallbackArgumentConversion(Book book) {
        assertEquals("42 Cats", book.getTitle());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe",
            "John, Doe"
    })
    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
        // perform assertions against person
        System.out.println(person.getFirstName());
        System.out.println(person.getLastName());
    }



}