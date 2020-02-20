package ru.costonied.examples.streams;


import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.function.BinaryOperator;

/**
 * Example showing how to work with stream terminate reduction method - reduce().
 * It combines stream into a single object.
 */
public class StreamReduceExample {
    public static void main(String[] args) {

        /* Reduce with 2 args: identity, accumulator */

        Stream<String> stream = Stream.of("h", "e", "l", "l", "o");
        String identity = "";       // The initial value for reduce. Should be the same type which returned by binaryOperator arg
        BinaryOperator<String> binaryOperator = (total_string, new_stream_element) -> total_string + new_stream_element;
        System.out.println(stream.reduce(identity, binaryOperator));


        /* Reduce with 1 arg: accumulator */
        // It will return the Optional<T> value because no initial value and stream could be empty

        Stream<String> stream_1 = Stream.of("h", "e", "l", "l", "o");
        Optional<String> optionalResult = stream_1.reduce(binaryOperator);
        optionalResult.ifPresent(System.out::println);


        /* Reduce with 3 args: identity, accumulator, combinator */
        // Such method allows Java to create intermediate reductions and then combine them at the end.
        // Commonly you should use it for parallel stream
        Stream<String> stream_2 = Arrays.asList("h", "e", "l", "l", "o").parallelStream();
        System.out.println(stream_2.reduce(identity, binaryOperator, binaryOperator));

    }
}
