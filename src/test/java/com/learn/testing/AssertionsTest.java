package com.learn.testing;

import com.learn.annotations.Adri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

//@Disabled
class AssertionsTest {

    @BeforeEach
    void setUsp(ExtensionContext context) throws Exception {
        System.out.println();
    }


    @Test
    void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {

        Person person = new Person("Adriana", "Suarez");

        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
        assertAll("person",
                () -> assertEquals("Adriana", person.getFirstName()),
                () -> assertEquals("Suarez", person.getLastName())
        );
    }


    @Test
    void dependentAssertions() {

        Person person = new Person("Adriana", "Suarez");


        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName, "is not null");

                    System.out.println("group 1 with a fail");

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("A")),
                            () -> assertTrue(firstName.endsWith("o"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    System.out.println("group 2");

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("S")),
                            () -> assertTrue(lastName.endsWith("z"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }


    @Test
    void notExceptionTesting() {
        assertDoesNotThrow(()->{
            new Person();
        });

        System.out.println();
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        int a =assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
            Thread.sleep(1000);
            return 2;
        });

        assertEquals(a, 2);
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsTest::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    private static String greeting() {
        return "Hello, World!";
    }

    /**
     * org.opentest4j.AssertionFailedError: execution exceeded timeout of 10 ms by 95 ms
     */
    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    /**
     * Differnece with assertTimeout: assertTimeoutPreemptively
     * does not wait unitl the end of the operation, and the execution is aborted when the expected timeout is aborted
     *
     * org.opentest4j.AssertionFailedError: execution timed out after 10 ms
     */
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }


}