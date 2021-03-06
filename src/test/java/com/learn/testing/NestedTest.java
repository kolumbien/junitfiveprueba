package com.learn.testing;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class NestedTest {

    Person person;

    @Disabled
    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        System.out.println("isInstantiatedWithNew");
    }

    @Disabled
    @Nested
    @DisplayName("when new")
    class WhenNew {
        @BeforeEach
        void createNewStack() {
            person = new Person();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            System.out.println("isEmpty");
            assertTrue(person.isEmpty());
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {
            @BeforeEach
            void pushAnElement() {
                person.setNames("ana", "perez");
                assertTrue(person.isEmpty());
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                System.out.println("isNotEmpty");
                assertFalse(person.isEmpty());
            }

            @Test
            @DisplayName("returns names")
            void returnElementWhenPopped() {
                System.out.println("returnElementWhenPopped");
                assertAll("properties",
                        () -> {
                            String firstName = person.getFirstName();
                            assertNotNull(firstName);
                            assertEquals(firstName, "ana");

                        },
                        () -> {

                            String lastName = person.getLastName();
                            assertNotNull(lastName);
                            assertEquals(lastName, "perez");

                        }
                );

            }
        }
    }

}
