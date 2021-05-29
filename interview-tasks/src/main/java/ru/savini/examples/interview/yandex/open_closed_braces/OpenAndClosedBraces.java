package ru.savini.examples.interview.yandex.open_closed_braces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenAndClosedBraces {
    private static int countOfBraces;
    private static byte[] bracesPairs;
    private static int theMostRightOpenedBraceIndex;
    private static int lastMovedOpenedBraceIndex;

    public static void main(String[] args) throws IOException {
        countOfBraces = getCountOfBraces();
        bracesPairs = new byte[2 * countOfBraces];
        theMostRightOpenedBraceIndex = countOfBraces - 1;

        initArray();
        do {
            printArray();
        } while (move());
    }

    private static int getCountOfBraces() throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return Integer.parseInt(bufferedReader.readLine());
        }
    }

    private static void initArray() {
        for (int i = 0; i < countOfBraces; i++) {
            bracesPairs[i] = '(';
        }
        for (int i = countOfBraces; i < countOfBraces * 2; i++) {
            bracesPairs[i] = ')';
        }
    }

    private static boolean move() {
        if (moveLastOpen()) {
            return true;
        } else {
            return movePreviousOpenAndShift();
        }
    }

    private static void printArray() throws IOException {
        System.out.write(bracesPairs);
        System.out.write('\n');
    }

    private static boolean moveLastOpen() {
        if (moveOpenBraces(theMostRightOpenedBraceIndex)) {
            theMostRightOpenedBraceIndex++;
            return true;
        } else {
            return false;
        }
    }

    private static boolean moveOpenBraces(int index) {
        int countOfClosedBraces = getCountOfClosedBracesFromRight(index);
        int countOfOpenBraces = getCountOfOpenedBracesFromRight(index);
        int countOfFreeClosedBraces = countOfClosedBraces - countOfOpenBraces;
        if ((countOfFreeClosedBraces - 1) <= 0) {
            return false;
        } else {
            bracesPairs[index] = ')';
            bracesPairs[index + 1] = '(';
            lastMovedOpenedBraceIndex = index + 1;
            return true;
        }
    }

    private static int getCountOfClosedBracesFromRight(int index) {
        return getCountOfBracesFromRight(index, ')');
    }

    private static int getCountOfOpenedBracesFromRight(int index) {
        return getCountOfBracesFromRight(index, '(');
    }

    private static int getCountOfBracesFromRight(int index, char braceType) {
        int count = 0;
        for (int i = index + 1; i < bracesPairs.length; i++) {
            if (bracesPairs[i] == braceType) {
                count++;
            }
        }
        return count;
    }

    private static boolean movePreviousOpenAndShift() {
        if (getCountOfPreviousOpenedBraces() > 1) {
            if (movePreviousOpen()) {
                shiftOpenToLeft();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean movePreviousOpen() {
        int previousOpenedIndex = theMostRightOpenedBraceIndex;
        while ((previousOpenedIndex = getPreviousOpenedIndex(previousOpenedIndex)) != 0) {
            if (moveOpenBraces(previousOpenedIndex)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    private static int getPreviousOpenedIndex(int index) {
        int i = index - 1;
        for (; i > 0; i--) {
            if (bracesPairs[i] == '(') {
                return i;
            }
        }
        return 0;
    }

    private static int getCountOfPreviousOpenedBraces() {
        int count = 0;
        for (int i = 0; i < theMostRightOpenedBraceIndex; i++) {
            if (bracesPairs[i] == '(') {
                count++;
            }
        }
        return count;
    }

    private static void shiftOpenToLeft() {
        int countOfOpenedBracesFromRight = getCountOfOpenedBracesFromRight(lastMovedOpenedBraceIndex);
        if (countOfOpenedBracesFromRight > 0) {
            for (int i = 1; i <= countOfOpenedBracesFromRight; i++) {
                bracesPairs[lastMovedOpenedBraceIndex + i] = '(';
                if (i == countOfOpenedBracesFromRight) {
                    theMostRightOpenedBraceIndex = lastMovedOpenedBraceIndex + i;
                }
            }
            for (int i = theMostRightOpenedBraceIndex + 1; i < bracesPairs.length; i++) {
                bracesPairs[i] = ')';
            }
        }
    }
}