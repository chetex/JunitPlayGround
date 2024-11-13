package com.luv2code.fizzbuzz;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class FizzBuzzIntStream implements IFizzBuzz {
    Logger logger = Logger.getLogger(FizzBuzz.class.getName());

    // Init hashmap dictionary
    private final Map<Integer, String> dictionaryMap = new HashMap<>();

    /**
     * Init dictionary map on every instance
     */
    public FizzBuzzIntStream () {
        try {
            dictionaryMap.put(3, "Fizz");
            dictionaryMap.put(5, "Buzz");
            dictionaryMap.put(0, "FizzBuzz");
        } catch (Exception error) {
            logger.log(Level.SEVERE, (Supplier<String>) error);
        }
    }

    /**
     * Test calculate computation given number
     * @param i Number to compute
     * @return Fizz if found / "" if not found
     */
    public String computeSpecificNumber(int i) {
        String exit = "";
        try {
            if (i % 3 != 0 && i % 5 != 0) exit = String.valueOf(i);
            if (i % 3 == 0) exit = dictionaryMap.get(3);
            if (i % 5 == 0) exit = dictionaryMap.get(5);
            if (i % 3 == 0 && i % 5 == 0) exit = dictionaryMap.get(0);
        } catch (Exception error) {
            logger.log(Level.SEVERE, (Supplier<String>) error);
        }
        return exit;
    }

    /**
     * Calculate fizz buzz sequence with IntStream
     *  >
     * @param n Number or elements to search
     * @return StringBuilder with elements comma separated
     */
    public StringBuilder calculate (int n) {
        StringBuilder result = new StringBuilder();
        try {
            IntStream.range(1, n)
                .mapToObj(result2 ->
                        switch (result2%3) {
                            case 0 -> dictionaryMap.get(0);
                            default -> throw new IllegalStateException("Unexpected value: " + result2%3);
                        }
                );
        } catch (Exception error) {
            logger.log(Level.SEVERE, (Supplier<String>) error);
        }
        return result;
    }
}
