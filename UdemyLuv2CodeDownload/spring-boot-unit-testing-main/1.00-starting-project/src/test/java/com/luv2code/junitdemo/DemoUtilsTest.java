package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This class will be used as JUnit testing class demo purpose
 */
public class DemoUtilsTest {
    // Logger
    Logger logger = Logger.getLogger(DemoUtilsTest.class.getName());

    /**
     * Equals and not equals
     */
    @Test
    public void testEqualsAndNotEquals () {
        try {
            // Instance DemoUtils class
            DemoUtils demoUtilsTest = new DemoUtils();

            // Test one method
            assertEquals(10, demoUtilsTest.add(1,9), "EQUALS 1 + 9 should be 10");
            assertNotEquals(3, demoUtilsTest.add(3,5), "Not equals 3 + 5 NOT 8");
        } catch (Exception error) {
            logger.log(Level.SEVERE, "Exception launched " + error);
        }
    }
}