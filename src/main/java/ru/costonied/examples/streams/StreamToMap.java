package ru.costonied.examples.streams;

import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;


/**
 * Example how to convert stream to Map
 */
public class StreamToMap {
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("One", "Two", "Three");

        Map<String, Integer> map = stream.collect(Collectors.toMap(s -> s, String::length));
//        Instead lambda function "s -> s" you could use Function.identity() they are the same
//        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

    }
}
