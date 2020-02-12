package ru.costonied.examples.concurrency;


import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;


/**
 * Example showing how to ExecutorService get Callable interface as parameter
 */
public class ExecutorServiceGetCallableExample {
    public static void main(String[] args) {

        ExecutorService executor = null;

        try {
            executor = Executors.newSingleThreadExecutor();

            Future<Integer> result = executor.submit(() -> {
                Thread.sleep(1000);
                return 27;
            });

            // Future.get() will waiting when lambda function finish
            // because our lambda implemented Callable (not Runnable)
            System.out.println("Result of executing is " + result.get());

        } catch (ExecutionException e) {
            System.out.println("Get ExecutionException");
        } catch (InterruptedException e) {
            System.out.println("Get InterruptedException");
        } finally {
            if (executor != null) executor.shutdown();
        }

    }
}
