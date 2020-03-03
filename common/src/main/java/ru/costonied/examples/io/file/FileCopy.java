package ru.costonied.examples.io.file;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * This class create input stream from File and output stream for another File.
 * After it copy paste data from one to another.
 */
public class FileCopy
{
    public static void main(String[] args) throws IOException
    {
        // Should be in module resources
        String inputFile = "test_data/io/file/input.txt";
        // Save in target to not make a garbage in project structure
        String outputFile = "target/output.txt";

        // Get class loader to find input file from module resources
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Use try-witch block order to have auto-closable streams
        try (InputStream inputStream = classLoader.getResourceAsStream(inputFile);
             FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {

            if (inputStream == null) {
                System.out.println("File [" + inputFile + "] is not exist in module resources!");
                return;
            }

            while (inputStream.available() > 0)
            {
                // Not performance copy-paste because not used byte buffer,
                // see overloaded method read() in docs if want performance.
                int data = inputStream.read();
                fileOutputStream.write(data);
            }
        }
    }
}