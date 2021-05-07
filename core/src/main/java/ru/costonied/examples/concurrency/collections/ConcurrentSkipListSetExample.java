package ru.costonied.examples.concurrency.collections;


import java.util.NavigableSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Showing how to work with ConcurrentSkipListSet collection.
 *
 * Commonly it just concurrent implementation of TreeSet collection,
 * it means that it just ordered Set which work well with concurrent.
 */
public class ConcurrentSkipListSetExample {
    public static void main(String[] args) {

        ExecutorService executor = null;

        // The best way is use interface NavigableSet for TreeSet or ConcurrentSkipListSet
        NavigableSet<Integer> set = new ConcurrentSkipListSet<>();

        // Task - generate random number and add to set
        Runnable runnableTask = () ->
                // Use a random number generator isolated to the current thread
                set.add(ThreadLocalRandom.current().nextInt(0, 99 + 1));

        try {
            // Executor work with several threads order to show real concurrent
            executor = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                // Execute several practically at the same time
                executor.execute(runnableTask);
            }

        } finally {
            if (executor != null) executor.shutdown();
        }

        // It's print ordered set of random integers
        System.out.println(set);

    }
}
