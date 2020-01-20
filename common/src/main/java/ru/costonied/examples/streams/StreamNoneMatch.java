package ru.costonied.examples.streams;


import java.util.stream.Stream;

/**
 * Simple example showing how to work noneMatch() for infinity stream.
 * In this example I want to show that noneMatch() operation could terminate an infinity stream well.
 */
public class StreamNoneMatch {
    public static void main(String[] args) {

        Stream<String> stream = Stream.iterate("+", s -> s + s);
        boolean result = stream.limit(2).peek(System.out::println).noneMatch(s -> s.length() > 3);
        System.out.println("noneMatch() result is " + result);

    }
}
