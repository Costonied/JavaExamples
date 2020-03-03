package ru.costonied.examples.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFileByLineToList {
    private static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // Save lines of file to List without cycle
        list = Files.readAllLines(Paths.get("test_data/io/file/input.txt"));
    }
}
