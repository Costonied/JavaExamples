package ru.costonied.examples.io.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Examples how you could read from console
 */
public class ReadFromConsole {

    public static void main(String[] args) {
        readByBufferedReader();
        readByScanner();
    }

    private static void readByBufferedReader() {
        /*
            Method 1
            Using BufferedReader class which very effective because you read data which store in buffer
        */
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input line: ");
            String line = buffer.readLine();
            System.out.println("Line from buffer: " + line);

            // It's ok to close Input Stream but if you close it here
            // then readByScanner() after readByBufferedReader() will raise exception
            // buffer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readByScanner() {
        /*
            Method 2
            Using Scanner class which could be useful for parse data
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input line: ");
        String line = scanner.nextLine();
        scanner.close();
        System.out.println("Line from scanner: " + line);
    }
}
