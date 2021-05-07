package ru.costonied.examples.lambdas;


import java.util.stream.IntStream;
import java.util.stream.DoubleStream;

/**
 * Simple example showing how to using class casting inside lambda
 */
public class LambdaCastingClass {
    public static void main(String[] args) {

        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.2, 4.5, 5.6);
        // If not using casting (int) inside the lambda then will be compile error "incompatible types: bad return type in lambda expression"
        IntStream intStream = doubleStream.mapToInt(s -> (int) s);

        intStream.forEach(System.out::println);

    }
}
