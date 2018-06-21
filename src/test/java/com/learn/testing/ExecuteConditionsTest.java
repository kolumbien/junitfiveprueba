package com.learn.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExecuteConditionsTest {

    @Disabled
    @Test
    void testWillBeSkipped() {
        System.out.println("This will never be executed");
    }

    @Test // Static JavaScript expression.
    @EnabledIf("2 * 3 == 6")
    void enableIfConditionTest() {
        System.out.println("TEnable if works");
    }

    @RepeatedTest(10) // Dynamic JavaScript expression.
    @DisabledIf("Math.random() < 0.314159")
    void disableIfTest() {
        System.out.println("mightNotBeExecuted");
    }

    @Test // Regular expression testing bound system property.
    @EnabledIf("/1.8/.test(systemProperty.get('java.specification.version'))")
    void enableIfSystemPropertyTest() {
        System.out.println("Im in test enableIfSystemPropertyTest");
        assertTrue(System.getProperty("java.specification.version").contains("1.8"));
    }

    @Test // Multi-line script, custom engine name and custom reason.
    // end::user_guide_scripts[]
    // @formatter:off
	// tag::user_guide_scripts[]
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
	// end::user_guide_scripts[]
	// @formatter:on
        // tag::user_guide_scripts[]
    void theDayAfterTomorrow() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        assertTrue(tomorrow.isAfter(today));
    }
// end::user_guide_scripts[]

}
