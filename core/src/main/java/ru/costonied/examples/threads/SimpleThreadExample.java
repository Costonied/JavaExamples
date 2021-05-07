package ru.costonied.examples.threads;


/**
 * The simplest example of the Thread.
 * Not use Concurrency API here at all.
 *
 * The output could be different because we can't manage the CPU time for Threads
 */
public class SimpleThreadExample {

    public static int countThread1 = 0;        // Be careful cause static variables not synchronised in this example
    public static int countThread2 = 0;
    public static int countThread3 = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread: started");


        /* The first way of creating new Thread - Runnable interface */

        new Thread(new ThreadExample1()).start();

        while (SimpleThreadExample.countThread1 < 400_000) {
            System.out.println("Main thread: still waiting countThread1");
            Thread.sleep(500);                          // The good practice is to sleep some time to not use a lot of CPU time
        }
        System.out.println("Main thread: countThread1 ready");


        /* The second way of creating new Thread - extends Thread */

        new ThreadExample2().start();

        while (SimpleThreadExample.countThread2 < 400_000) {
            System.out.println("Main thread: still waiting countThread2");
            Thread.sleep(500);                          // The good practice is to sleep some time to not use a lot of CPU time
        }
        System.out.println("Main thread: countThread2 ready");


        /* The third way of creating new Thread - lambda with Thread */

        // Set lambda expression which implemented Runnable interface
        // Don't forget the start() method in the end
        new Thread(() -> {
            System.out.println("Lambda thread: started");
            for (int i = 0; i < 500_000; i++) {
                SimpleThreadExample.countThread3++;
            }
            System.out.println("Lambda thread: finished");
        }).start();

        while (SimpleThreadExample.countThread3 < 400_000) {
            System.out.println("Main thread: still waiting countThread3");
            Thread.sleep(500);                          // The good practice is to sleep some time to not use a lot of CPU time
        }
        System.out.println("Main thread: countThread3 ready");


        System.out.println("Main thread: finished");
    }

}

/**
 * The first way how to create the Thread.
 * Implements Runnable interface.
 * It's better way to create your own Threads.
 */
class ThreadExample1 implements Runnable{
    /**
     * Just increase static counter
     */
    @Override
    public void run() {
        System.out.println("ThreadExample1: started");
        for (int i = 0; i < 500_000; i++) {
            SimpleThreadExample.countThread1++;
        }
        System.out.println("ThreadExample1: finished");
    }
}

/**
 * The first way how to create the Thread.
 * Extends Thread class.
 * Use it just if you need to manage of priority of Thread.
 */
class ThreadExample2 extends Thread{
    @Override
    public void run() {
        System.out.println("ThreadExample2: started");
        for (int i = 0; i < 500_000; i++) {
            SimpleThreadExample.countThread2++;
        }
        System.out.println("ThreadExample2: finished");
    }
}
