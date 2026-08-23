package org.example;

import java.util.HashMap;
import java.util.Map;

public class FindMostFrequent {
    public static void main(String[] args) {

        char res = findMostFrequent("");
        System.out.println(res);
    }

// Given: phrase of type String.
// Required: Create a function with name findMostFrequent, which takes the given phrase as a parameter. The function returns the most frequent character in the phrase. If there are multiple characters with the same number of occurences, return the one closer to the beginning of the phrase.
// Requirements:
// * all characters must be lowercase before comparison
//* if the phrase is null or empty, throw IllegalArgumentException
//* spaces must be skipped
// Input: "The world is full of obvious things which nobody by any chance ever observes."
// Output: 'o'.

    public static char findMostFrequent(String input) {

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        input = input.toLowerCase();

        Map<Character, Integer> allSymbols = new HashMap<>();

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            allSymbols.put(chars[i], allSymbols.getOrDefault(chars[i], 0) + 1);
        }

        char mostFrequent = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : allSymbols.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }

        return mostFrequent;

    }
}