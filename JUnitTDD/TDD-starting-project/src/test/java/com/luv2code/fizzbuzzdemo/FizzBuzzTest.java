package com.luv2code.fizzbuzzdemo;

import com.luv2code.fizzbuzz.FizzBuzz;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all TDD testing over {@link FizzBuzz}
 *  > Any number divisible by three is replaced by the word fizz and any number divisible by five
 *  by the word buzz. Numbers divisible by both three and five (i.e. divisible by fifteen) become fizz buzz.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FizzBuzzTest {

    // Init fizz buzz class global for all class
    private static FizzBuzz fizzBuzz;

    @BeforeAll
    public static void initBeforeAll() {
        fizzBuzz = new FizzBuzz();
    }

    /**
     * Test fizz buzz program
     * > Init dictionary
     * > Call dictionary and check if everything goes well.
     * > Check if it is fail or not.
     */
    @DisplayName("Test fizz buzz program")
    @Test
    public void testFizzBuzzProgram() {
        // Assumptions
        List<String> expectedDictionary = new ArrayList<>() {};
        expectedDictionary.add("Fizz");
        expectedDictionary.add("Buzz");
        expectedDictionary.add("FizzBuzz");

        // CallsR
        List<String> responseFizz = fizzBuzz.executeFizzBuzz();

        // Assertion
        Assertions.fail("fail!!");
    }
}