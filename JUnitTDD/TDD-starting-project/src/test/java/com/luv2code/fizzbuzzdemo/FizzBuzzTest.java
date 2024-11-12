package com.luv2code.fizzbuzzdemo;

import com.luv2code.fizzbuzz.FizzBuzz;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("Test fizz buzz program divisible by three")
    @Test
    public void testForDivisibleByThree() {
        // Assumptions
        String outputFizz = "Fizz";

        // CallsR
        String response = fizzBuzz.compute(6);

        // Assertion
        Assertions.assertEquals(outputFizz, response, "Is divisible by three");
    }

    /**
     * Test fizz buzz program
     * > Init dictionary
     * > Call dictionary and check if everything goes well.
     * > Check if it is fail or not.
     */
    @DisplayName("Test fizz buzz program divisible by five 5")
    @Test
    public void testForDivisibleByFive() {
        // Assumptions
        String outputFizz = "Buzz";

        // CallsR
        String response = fizzBuzz.compute(5);

        // Assertion
        Assertions.assertEquals(outputFizz, response, "Is divisible by five");
    }

    /**
     * Test fizz buzz program
     * > Init dictionary
     * > Call dictionary and check if everything goes well.
     * > Check if it is fail or not.
     */
    @DisplayName("Test fizz buzz program divisible by five and three")
    @Test
    public void testForDivisibleByThreeAndFive() {
        // Assumptions
        String outputFizz = "FizzBuzz";

        // CallsR
        String response = fizzBuzz.compute(15);

        // Assertion
        Assertions.assertEquals(outputFizz, response, "Is divisible by five and three");
    }

    /**
     * Test fizz buzz program
     * > Init dictionary
     * > Call dictionary and check if everything goes well.
     * > Check if it is fail or not.
     */
    @DisplayName("Test fizz buzz program divisible by five and three")
    @Test
    public void testFizzBuzz() {
        // Assumptions
        String outputFizz = "FizzBuzz";

        // CallsR
        String response = fizzBuzz.compute(15);

        // Assertion
        Assertions.assertEquals(outputFizz, response, "Is divisible by five and three");
    }

    /**
     * Parametric tests
     * > Modified @Test instead @ParameterizedTest
     * > Read from @CsvParametizer
     */
    @DisplayName("Test fizz buzz program divisible by five and three")
    @ParameterizedTest(name = "value{0}, expected={1}")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, Fizz",
            "4, 4",
            "5, Buzz",
            "6, Fizz",
            "7, 7"
    })
    public void testFizzBuzz(String param, String value) {
        // Assertion
        Assertions.assertEquals(value, fizzBuzz.compute(Integer.valueOf(param)), "Is divisible CSV file");
    }
}