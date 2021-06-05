package ru.savini.examples.interview.delete_duplicates;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteDuplicates {
    private static final int MAX_STRING_SIZE = 12;

    public static void main(String[] args) throws Exception {

        char[] previousValue = new char[MAX_STRING_SIZE];
        char[] currentValue = new char[MAX_STRING_SIZE];

        BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        if (n < 1) {
            return;
        }
        readNumber(bufferedReader, previousValue);
        writeNumber(bufferedWriter, previousValue);

        for (int i = 1; i < n; i++) {
            readNumber(bufferedReader, currentValue);
            if (isCurrentEqualPrevious(currentValue, previousValue)) {
                continue;
            } else {
                copyCurrentToPrevious(currentValue, previousValue);
                writeNumber(bufferedWriter, currentValue);
            }
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void readNumber(BufferedReader bufferedReader, char[] buffer) throws IOException {
        int b;
        for (int i = 0; i < MAX_STRING_SIZE; i++) {
            b = bufferedReader.read();
            if (b < 0) {
                buffer[i] = '\n';
                break;
            }
            buffer[i] = (char) b;
            if (b == '\n') {
                break;
            }
        }
    }

    private static boolean isCurrentEqualPrevious(char[] current, char[] previous) {
        for (int i = 0; i < MAX_STRING_SIZE; i++) {
            if (current[i] != previous[i]) {
                return false;
            }
        }
        return true;
    }

    private static void copyCurrentToPrevious(char[] current, char[] previous) {
        for (int i = 0; i < MAX_STRING_SIZE; i++) {
            previous[i] = current[i];
        }
    }

    private static void writeNumber(BufferedWriter bufferedWriter, char[] number) throws IOException {
        for (int i = 0; i < MAX_STRING_SIZE; i++) {
            if (number[i] != '\n') {
                bufferedWriter.write(number[i]);
            } else {
                bufferedWriter.write(number[i]);
                break;
            }
        }
    }
}
