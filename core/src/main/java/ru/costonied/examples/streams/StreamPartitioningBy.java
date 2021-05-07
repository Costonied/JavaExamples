package ru.costonied.examples.streams;


import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Simple example showing how to use Collectors.partitioningBy()
 */
public class StreamPartitioningBy {
    public static void main(String[] args) {

        /*
         * Use Collectors.partitioningBy() just with one param Predicate <? super T> predicate
         * Such Collector return List<String> as a value for Map
         */
        Stream<String> stream1 = Stream.of("One", "Two", "Three");
        Map<Boolean, List<String>> mapOfList = stream1.collect(Collectors.partitioningBy(s -> s.length() == 3));
        System.out.println(mapOfList);

        /*
         * Use Collectors.partitioningBy() with two params:
         *   - Predicate<? super T> predicate
         *   - Collector<? super T, A, D> downstream
         * Such Collector could return not only List as a value for Map but Set also
         */
        Stream<String> stream2 = Stream.of("One", "Two", "Three");
        Map<Boolean, Set<String>> mapOfSet =
                stream2.collect(Collectors.partitioningBy(s -> s.length() == 3, Collectors.toSet()));
        System.out.println(mapOfSet);

    }
}
