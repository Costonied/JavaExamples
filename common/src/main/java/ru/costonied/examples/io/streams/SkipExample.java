package ru.costonied.examples.io.streams;


import java.io.IOException;
import java.io.StringReader;

/**
 * Showing how to work with skip() in IO Streams
 */
public class SkipExample {
    public static void main(String[] args) throws IOException {

        StringReader reader = new StringReader("ABCDE");

        /* Skip 1 char */
        System.out.print((char)reader.read());
        // skippedChars will contain 1 because 1 char was skipped
        long skippedChars = reader.skip(1);
        System.out.print(":" + skippedChars + " chars was skipped:");
        System.out.print((char)reader.read());

        /* Skip a lot of char, more then left in the String */
        // skippedChars will contain just 2 because just 2 chars left in the input
        skippedChars = reader.skip(10);
        System.out.print(":" + skippedChars + " chars was skipped:");
        // Empty string because read() will return "-1" because end has been reached
        System.out.print((char)reader.read());

        // Final output: "A:1 chars was skipped:C:2 chars was skipped:"

    }
}
