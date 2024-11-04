package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class will be used as JUnit testing class demo purpose
 */
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class DemoUtilsTest {
    // Logger
    private static final Logger logger = Logger.getLogger(DemoUtilsTest.class.getName());

    // Global DemoUtils obj
    private static DemoUtils demoUtils;

    /**
     * Setup before each method
     */
    @BeforeAll
    public static void beforeAll() {
        try {
            demoUtils = new DemoUtils();

            logger.log(Level.INFO, "Esto se ejecuta SOLO UNA VEZ de cada metodo");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }

    /**
     * Setup before each method
     */
    @BeforeEach
    public void beforeEach() {
        try {
            logger.log(Level.INFO, "Esto se ejecuta antes de cada metodo");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }

    /**
     * Equals and not equals
     */
    @Test
    @DisplayName("Test equals and not equals in DemoUtils add method")
    public void testEqualsAndNotEquals () {
        try {
            // Test one method
            assertEquals(10, demoUtils.add(1,9), "EQUALS 1 + 9 should be 10");
            assertNotEquals(3, demoUtils.add(3,5), "Not equals 3 + 5 NOT 8");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }

    /**
     * Testing true and false options.
     *  > Given number testing if number is greater or not
     */
    @Test
    @DisplayName("Testing true and false")
    public void testTrueAndFalse() {
        try {
            // Declare two integers and check if both numbers are greater or not
            int firstNumber = 5;
            int secondNumber = 6;

            // Testing if number second is greater than first number
            assertTrue(demoUtils.isGreater(secondNumber, firstNumber));

            // Testing if response is the same or not
            assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Is the same object");
            assertNotSame(firstNumber, demoUtils.getAcademyInList());
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception raised" + error);
        }
    }

    /**
     * Test to verify if exception is thrown.
     */
    @Test
    public void testThrowException () {
        try {
            // Initialize var and check if it is an exception
            int a = 1;
            int b = -9;

            assertThrows(Exception.class, () -> demoUtils.throwException(b));
            assertDoesNotThrow(() -> demoUtils.throwException(a));
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception with " + error);
        }
    }

    /**
     * Check if exception is launch after considerable timeout
     */
    @Test
    public void testCheckTimeout() {
        try {
            // Launch after considerable timeout
            Long timeout = 5L;

            assertTimeoutPreemptively(Duration.ofSeconds(3), ()-> { demoUtils.checkTimeout(); } );
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception raised" + error);
        }
    }

    /**
     * Iterate over iterable lists and check they are the same
     */
    @Test
    public void testIterableListsAreTheSame() {
        try {
            // Declare a iterable to ckeck if it is the same
            List<String> iterableList = List.of("luv", "2", "code");

            // Check with assertion list is the same
            assertIterableEquals(iterableList, demoUtils.getAcademyInList());
            assertLinesMatch(iterableList, demoUtils.getAcademyInList());
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception raised" + error);
        }
    }

    /**
     * Test not null
     */
    @Test
    public void testObjIsNotNull () {
        try {
            // Test one method
            assertNotNull(demoUtils, "DemoUtils obj not null");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }

    /**
     * Test not null by string
     *  - Given two strings test not null
     */
    @Test
    public void testNotNullByString () {
        try {
            String oneNull = null;
            String second = "test";

            // Test one method
            assertNull(demoUtils.checkNull(oneNull), "DemoUtils obj IS null");
            assertNotNull(demoUtils.checkNull(second), "String is not null");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }

    /**
     * Setup after each method
     */
    @AfterAll
    public static void afterAll() {
        try {
            // Remove obj assign
            demoUtils = null;

            logger.log(Level.INFO, "Esto se ejecuta SOLO UNA VEZ de despues metodo");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }
}