package ru.costonied.examples.threads;

import static ru.costonied.examples.logging.LoggerExample.info;

public class DeadLockWithTwoResourcesExample {
    private static final Lock lockOne = new Lock();
    private static final Lock lockTwo = new Lock();

    public static void main(String[] args) {
        Thread threadOne = new Thread(getTaskLockOneThenTwo());
        Thread threadTwo = new Thread(getTaskLockTwoTheOne());

        threadOne.start();
        threadTwo.start();
    }

    private static Runnable getTaskLockOneThenTwo() {
        return () -> {
            try {
                info("ThreadOne try to get lockOne");
                synchronized (lockOne) {
                    info("ThreadOne get lockOne");
                    Thread.sleep(5000);
                    info("ThreadOne try to get lockTwo");
                    synchronized (lockTwo) {
                        // never be here
                        info("ThreadOne get lockTwo");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    private static Runnable getTaskLockTwoTheOne() {
        return () -> {
            try {
                info("ThreadTwo try to get lockTwo");
                synchronized (lockTwo) {
                    info("ThreadTwo get lockTwo");
                    Thread.sleep(5000);
                    info("ThreadTwo try to get lockOne");
                    synchronized (lockOne) {
                        // never be here
                        info("ThreadTwo get lockOne");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }
}

class Lock {

}
