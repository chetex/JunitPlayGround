package com.luv2code.junitdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class will be used as JUnit testing class demo purpose
 */
public class DemoUtilsTest {
    // Logger
    Logger logger = Logger.getLogger(DemoUtilsTest.class.getName());

    // Global DemoUtils obj
    private DemoUtils demoUtils;

    /**
     * Setup before each method
     */
    @BeforeEach
    public void beforeEach() {
        try {
            demoUtils = new DemoUtils();
            logger.log(Level.INFO, "@BeforeEach executed before each execution test");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }

    /**
     * Equals and not equals
     */
    @Test
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
}