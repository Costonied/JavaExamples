package ru.costonied.examples.io.streams;

import java.util.List;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

/**
 * Showing how to work with BufferedReader and BufferedWriter streams
 */
public class BufferedReaderWriterExample {

    public static void main(String[] args) throws IOException {

        // Should be in module resources
        String inputFile = "test_files/input.txt";
        // Save in target to not make a garbage in project structure
        String outputFile = "target/output.txt";

        // Get class loader to find input file from module resources
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(inputFile);
        assert inputStream != null;
        InputStreamReader sourceFile = new InputStreamReader(inputStream);

        writeFiles(readFile(sourceFile), outputFile);

    }

    /**
     * Read lines from file and save it to List
     * @param inputStreamReader source file
     * @return list of lines
     * @throws IOException
     */
    public static List<String> readFile(InputStreamReader inputStreamReader) throws IOException {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                lines.add(readLine);
            }
        }
        return lines;
    }

    /**
     * Write lines from List to destination file
     *
     * The empty string will be added.
     * The string just with whitespaces will be added but whitespaces will be deleted.
     *
     * @param lines list of strings
     * @param outputFile file destination
     * @throws IOException
     */
    public static void writeFiles(List<String> lines, String outputFile) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            if (lines != null) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                    writer.flush();
                }
            }
        }

    }

}
