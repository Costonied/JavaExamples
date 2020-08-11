package ru.costonied.examples.concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Simple example with ThreadPoolExecutor.
 * Get ThreadPoolExecutor from ExecutorService and increase number of threads in the pool
 */
public class ThreadPoolExecutorExample {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = null;

        /* Example with newFixedThreadPool */

        try {
            // Get ThreadPoolExecutor with 2 threads in pool
            threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
            // And we decided to change number of threads in the pool
            // Increase core and maximum pool size to 4
            threadPoolExecutor.setCorePoolSize(4);
            threadPoolExecutor.setMaximumPoolSize(4);
        } finally {
            if (threadPoolExecutor != null) {
                threadPoolExecutor.shutdown();
                threadPoolExecutor = null;
            }
        }

        /* Example with newSingleThreadExecutor */

        try {
            // Try to get ThreadPoolExecutor from newSingleThreadExecutor (always just 1 thread in the pool)
            threadPoolExecutor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        } catch (ClassCastException e) {
            // This program will be here! Because it's not a ThreadPoolExecutor
            System.out.println("Can't cast ExecutorService (from newSingleThreadExecutor) to ThreadPoolExecutor");
            // So you can't for example increase the number of threads in the pool because there is always just 1
        } finally {
            if (threadPoolExecutor != null) threadPoolExecutor.shutdown();
        }

    }
}
