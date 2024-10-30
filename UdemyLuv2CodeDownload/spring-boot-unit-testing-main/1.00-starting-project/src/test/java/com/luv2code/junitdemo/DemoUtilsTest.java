package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

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