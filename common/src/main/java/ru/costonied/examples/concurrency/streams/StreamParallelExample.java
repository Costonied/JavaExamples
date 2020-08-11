package ru.costonied.examples.concurrency.streams;

import java.util.Arrays;
import java.util.List;

/**
 * Show how to work stream.parallel() function.
 * It split the stream into several streams which processed
 * in additional threads.
 * Output (count of threads) depends on hardware, OS and JVM.
 */
public class StreamParallelExample {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = list.stream()
                .parallel()
                .peek(integer -> System.out.println(integer + ": " + Thread.currentThread().getName()))
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum is: " + sum);

    }
}
