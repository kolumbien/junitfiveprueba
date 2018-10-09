package com.learn.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://github.com/junit-team/junit5/blob/master/documentation/src/test/java/example/ConditionalTestExecutionDemo.java

@Disabled
public class ExecuteConditionsTest {

    @Disabled
    @Test
    void testWillBeSkipped() {
        System.out.println("This will never be executed");
    }

    @Disabled
    @Test // Static JavaScript expression.
    @EnabledIf("2 * 3 == 6")
    void enableIfConditionTest() {
        System.out.println("TEnable if works");
    }

    @Disabled
    @RepeatedTest(value = 10,  name = "{displayName} {currentRepetition}/{totalRepetitions}")
    // Dynamic JavaScript expression.
    @DisabledIf("Math.random() < 0.314159")
    void disableIfTest() {
        System.out.println("mightNotBeExecuted");
    }

    @Disabled
    @Test // Regular expression testing bound system property.
    @EnabledIf("/1.8/.test(systemProperty.get('java.specification.version'))")
    void enableIfSystemPropertyTest() {
        System.out.println("Im in test enableIfSystemPropertyTest");
        assertTrue(System.getProperty("java.specification.version").contains("1.8"));
    }

    @Disabled
    @RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Details... :: repetition 1 of 3");
    }

    /**
     * Accessing Java packages and classes from script using Nashorn extensions
     * http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/
     */
    @Disabled
    @Test // Multi-line script, custom engine name and custom reason.
	@EnabledIf(value = {
					"load('nashorn:mozilla_compat.js')",
					"importPackage(java.time)",
					"",
					"var today = LocalDate.now()",
					"var tomorrow = today.plusDays(1)",
					"tomorrow.isAfter(today)"
				},
				engine = "nashorn",
				reason = "Self-fulfilling: {result}")
    void theDayAfterTomorrow() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        assertTrue(tomorrow.isAfter(today));
    }


}
