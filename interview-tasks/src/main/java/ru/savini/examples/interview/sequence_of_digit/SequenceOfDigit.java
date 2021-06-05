package ru.savini.examples.interview.sequence_of_digit;

import java.io.BufferedReader;
import java.io.FileReader;

public class SequenceOfDigit {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));

        int size = Integer.valueOf(r.readLine());
        int[] sequence = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            sequence[i] = Integer.valueOf(r.readLine());
        }

        System.out.println(longestSequence(sequence));
    }

    public static int longestSequence(int[] vector) {
        int maxLength = 0;
        int currentLength = 0;

        for (int i = 0; i <= vector.length - 1; i++) {
            if (vector[i] == 1) {
                currentLength++;
                maxLength = (currentLength > maxLength) ? currentLength : maxLength;
            } else {
                currentLength = 0;
            }
        }

        return maxLength;
    }
}
