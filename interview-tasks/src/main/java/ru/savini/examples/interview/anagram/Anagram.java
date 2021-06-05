package ru.savini.examples.interview.anagram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Anagram {
    private static boolean isAnagram;
    private static boolean isInterrupted;
    private static String originalWord;
    private static String anagramWord;
    private static Map<Character, Short> originalWordChars;
    private static BufferedReader inputStream;

    public static void main(String[] args) throws IOException {
        init();
        readWords();
        calculateCharsFromOriginalWord();
        compareAnagramCharsWithOriginalWordChars();
        if (!isInterrupted) {
            checkAnagrams();
        }
        printResult();
    }

    private static void init() throws FileNotFoundException {
        isAnagram = false;
        originalWordChars = new HashMap<>();
        inputStream = new BufferedReader(new FileReader("input.txt"));
    }

    private static void readWords() throws IOException {
        originalWord = inputStream.readLine();
        anagramWord = inputStream.readLine();
        inputStream.close();
    }

    private static void calculateCharsFromOriginalWord() {
        for (char c : originalWord.toCharArray()) {
            if (originalWordChars.containsKey(c)) {
                short count = originalWordChars.get(c);
                originalWordChars.put(c, ++count);
            } else {
                originalWordChars.put(c, (short) 1);
            }
        }
    }

    private static void compareAnagramCharsWithOriginalWordChars() {
        for (char c : anagramWord.toCharArray()) {
            if (originalWordChars.containsKey(c)) {
                short count = originalWordChars.get(c);
                originalWordChars.put(c, --count);
            } else {
                isInterrupted = true;
                isAnagram = false;
                break;
            }
        }
    }

    private static void checkAnagrams() {
        for (Short value : originalWordChars.values()) {
            if (value != (short) 0) {
                isAnagram = false;
                return;
            }
        }
        isAnagram = true;
    }

    private static void printResult() {
        if (isAnagram) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
