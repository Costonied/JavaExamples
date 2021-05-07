package ru.costonied.examples.concurrency.executors;


import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Simple example showing how to execute something in another Thread using Concurrency API
 */
public class ExecutorServiceExample {
    public static void main(String[] args) {

        ExecutorService executorService = null;

        try {
            executorService = Executors.newSingleThreadExecutor();

            /* One way of execute command in new thread is using execute() */
            executorService.execute(
                    () -> System.out.println("Hello! I'm new executed thread and my Thread is " + Thread.currentThread().getName()));

            /* Another way is using submit() */
            // Don't want to show here abilities of Future interface but want to show that you could save result of submit()
            Future<?> future = executorService.submit(
                    () -> System.out.println("Hello! I'm new submitted thread and my Thread is " + Thread.currentThread().getName()));
        } finally {
            if (executorService != null) executorService.shutdown();
        }

    }
}
