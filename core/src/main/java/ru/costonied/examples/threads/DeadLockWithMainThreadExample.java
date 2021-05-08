package ru.costonied.examples.threads;


import static ru.costonied.examples.logging.LoggerExample.info;

/**
 * The simplest deadlock example.
 * Main thread get lock to object, start child thread and join child thread.
 * But child thread try to get lock on object which already locked by main thread.
 */
public class DeadLockWithMainThreadExample {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        synchronized (LOCK) {
            info("Main thread get lock");
            Thread thread1 = new Thread(getTask());
            thread1.start();
            info("Main thread join child thread");
            thread1.join();
            info("Main thread never waited child thread");
        }
    }

    private static Runnable getTask() {
        return () -> {
            try {
                info("Child thread started");
                Thread.sleep(5000);
                info("Child thread try to get lock");
                synchronized (LOCK) {
                    // do nothing
                    info("Child thread never get lock");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }
}
