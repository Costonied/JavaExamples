package ru.costonied.examples.nio;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Simple example showing how to check file existing
 */
public class FilesExistExample {
    public static void main(String[] args) throws IOException {

        Path fileOrDirectory = Paths.get("/tmp/not_exist.txt");
        // Print false because I haven't this file or directory
        System.out.println(Files.exists(fileOrDirectory));

    }
}
