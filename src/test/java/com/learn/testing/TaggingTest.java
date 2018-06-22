package com.learn.testing;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TaggingTest {

    @Tag("fast")
    @Tag("model")
    class TaggingDemo {

        @Test
        @Tag("taxes")
        void testingTaxCalculation() {
        }

    }

}
