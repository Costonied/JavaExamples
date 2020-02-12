package ru.costonied.examples.concurrency;


import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Showing how to waiting terminating of all tasks in the Thread
 */
public class ExecutorServiceAwaitTermination {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = null;

        // Create Callable lambda task order to reuse it below.
        // Task just waiting one second.
        Callable<?> task = () -> {
            Thread.sleep(1000);
            return null;
        };

        try {
            System.out.println(LocalTime.now());                // Print current time
            executor = Executors.newSingleThreadExecutor();
            executor.submit(task);                              // Add several tasks to executor
            executor.submit(task);
            executor.submit(task);
        } finally {
            // Here we just shutdown ExecutorService but tasks in the created Thread will continue
            if (executor != null) executor.shutdown();
        }

        // Wait of termination 2 seconds
        if (executor != null) executor.awaitTermination(2, TimeUnit.SECONDS);

        // isTerminated() show false because three tasks was added so it'll take total 3 seconds
        System.out.println("Is executor terminated? : " + executor.isTerminated());
        System.out.println(LocalTime.now());
    }
}
