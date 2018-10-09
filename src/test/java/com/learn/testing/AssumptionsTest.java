package com.learn.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Disabled
public class AssumptionsTest {


    /**
     * You would use assume if you have circumstances under which some tests should not run at all.
     * "Not run" means that it cannot fail, because, well, it did not run.
     *
     * You would use assert to fail a test if something goes wrong.
     */

    /*
    *   org.opentest4j.TestAbortedException: Assumption failed: assumption is not true
     * */
    @Disabled
    @Test
    void assumeTrueTest() {
        assumeTrue("CI".equals("bar"));
        // remainder of test

        System.out.println("hello bar");
    }

    /**
     * org.opentest4j.AssertionFailedError:
     * Expected :<true>
     * Actual   :<false>
     */
    @Disabled
    @Test
    void assertTrueTest() {
        assertTrue("CI".equals("bar"));
        // remainder of test

        System.out.println("hello bar");
    }

    @Disabled
    @Test
    void assumingThatTest() {
        assumingThat("CI".equals("CI"),
                () -> {
                    // perform these assertions only on CI==CI
                    System.out.println("assumeThat test");
                    assertEquals(2, 2);
                });

        System.out.println("after assumeThat test");
        // perform these assertions in all environments
        assertEquals("a string", "a string");
    }
}
