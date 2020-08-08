package ru.costonied.examples.concurrency.executors;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Show how to work with ScheduledExecutorService.
 */
public class ScheduledExecutorServiceExample {

    private static String msg = "%s - %s : %s\n";

    public static void main(String[] args) {

        System.out.printf(msg, LocalTime.now(), Thread.currentThread().getName(), "started");

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        // Start task in cycle:
        // cycle started after init delay in 2 seconds,
        // then task will be repeated every 5 seconds
        scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.printf(msg, LocalTime.now(), Thread.currentThread().getName(), "scheduled task"),
                2, 5, TimeUnit.SECONDS);

        System.out.printf(msg, LocalTime.now(), Thread.currentThread().getName(), "stopped");

    }
}
