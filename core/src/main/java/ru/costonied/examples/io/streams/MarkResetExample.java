package ru.costonied.examples.io.streams;


import java.io.IOException;
import java.io.StringReader;

/**
 * Show how to use mark() and reset() operations in IO Streams
 */
public class MarkResetExample {
    public static void main(String[] args) throws IOException {

        StringReader reader = new StringReader("ABCD");

        System.out.print((char)reader.read());

        if (reader.markSupported()) {

            reader.mark(100);

            System.out.print((char)reader.read());
            System.out.print((char)reader.read());

            reader.reset();
            System.out.print(":reset:");

            System.out.print((char)reader.read());
            System.out.print((char)reader.read());
            System.out.print((char)reader.read());
        }

        reader.close();

    }
}
