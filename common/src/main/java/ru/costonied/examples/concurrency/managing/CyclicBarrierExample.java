package ru.costonied.examples.concurrency.managing;


import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.BrokenBarrierException;

/**
 * Show how to work with CyclicBarrier for managing process workflow.
 *
 * You could play wih count of threads in pool and barrier threshold.
 * After threshold was reached the barrier breaks and set own count to zero
 * so you could reuse barrier after it again.
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {

        ExecutorService executor = null;
        Worker worker = new Worker(new CyclicBarrier(3, () -> System.out.println("*** The barrier was broken")));

        try {

            executor = Executors.newFixedThreadPool(3);
            for (int i = 0; i < 3; i++) {
                executor.execute(worker::doWork);
            }

        } finally {

            if (executor != null) executor.shutdown();

        }
    }
}

/**
 * Worker which do some job
 */
class Worker {

    private CyclicBarrier barrier;

    Worker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    void doWork() {
        String infoMessage = "%s - Worker %s in thread %s";
        System.out.println(String.format(infoMessage, LocalTime.now(), "start", Thread.currentThread().getName()));
        try {
            Thread.sleep(500);
            // Here Thread will stop and wait barrier broke
            barrier.await();
            // Thread continue work after barrier will reach the threshold
            Thread.sleep(500);
        } catch (InterruptedException | BrokenBarrierException e) {
            // do nothing
        }
        System.out.println(String.format(infoMessage, LocalTime.now(), "finish", Thread.currentThread().getName()));
    }

}
