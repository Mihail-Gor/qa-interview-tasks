package org.example;

/*
* Task 2 :
Given: phrase of type String.
Required: Create a function with name findMostFrequent, which takes the given phrase as a parameter.
* The function returns the most frequent character in the phrase. If there are multiple characters with the same number of occurences,
* return the one closer to the beginning of the phrase.
Requirements:
* all characters must be lowercase before comparison
* if the phrase is null or empty, throw IllegalArgumentException
* spaces must be skipped
Input: "The world is full of obvious things which nobody by any chance ever observes."
Output: 'o'.
* */

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class FindMostFrequentFinishedResult {
//    public static Character findMostFrequent(String phrase) {
////        String [] phraseArray = phrase.split("").;
//        char[] phraseArray = phrase.toCharArray();
//        var frequency = new LinkedHashMap<Character, Integer>();
//        int max = 0;
//        char value;
//        for (int i = 0; i < phraseArray.length; i++) {
//            frequency.put(phraseArray[i], frequency.get(phraseArray[i]) + 1);
//            if (frequency.get(phraseArray[i]) > max) {
//               max = frequency.get(phraseArray[i]);
//               value = phraseArray[i];
//            }
//        }
////        for (int i = 0; i < frequency.entrySet().size(); i++) {
////            if (frequency.get())
////        }
//        return frequency.entrySet().stream().max((e,v)-> e.getValue());
//    }

    public static void main(String[] args) {
        System.out.println(findMostFrequent("asfafsffasffwafaaags"));
    }

    public static Character findMostFrequent(String parameter) {

        if (parameter.isBlank()) {
            throw new IllegalArgumentException("Errors !!!");
        }

        String lowercase = parameter.toLowerCase(Locale.ROOT).trim();

        Map<Character, Integer> result = new HashMap<>();

        char[] characterList = lowercase.toCharArray();

        for (char ch : characterList) {
            if (ch == ' ') {
                continue;
            } else {
                if (result.isEmpty()) {
                    result.put(ch, 1);


                } else {
                    boolean a = result.containsKey(ch);
                    if (a) {
                        Integer b = result.get(ch);
                        result.put(ch, ++b);
                    } else {
                        result.put(ch, 1);
                    }
                }
            }

        }

        var max = result.values().stream().max(Integer::compareTo).orElse(0);

        Character myResult = ' ';

        for (Map.Entry<Character, Integer> set: result.entrySet()) {
            if(max == set.getValue()) {
                myResult = set.getKey();
            }
        }

        return myResult;
    }
}