package ru.costonied.examples.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFileByLineToList {
    private static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException, URISyntaxException {
        URL url = ReadFileByLineToList.class.getClassLoader().getResource("test_files/input.txt");
        list = Files.readAllLines(Paths.get(url.toURI()));
        list.forEach(System.out::println);
    }
}
