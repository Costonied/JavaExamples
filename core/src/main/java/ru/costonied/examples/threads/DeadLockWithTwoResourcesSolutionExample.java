package ru.costonied.examples.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * The good solution to prevent deadlocks is
 * to have unique Id on locking object.
 * So you could control the order of resources locking.
 */
public class DeadLockWithTwoResourcesSolutionExample {
    private static final LockWithId lockOne = new LockWithId(1);
    private static final LockWithId lockTwo = new LockWithId(2);
    private static final List<LockWithId> locksFirst = new ArrayList<>();
    private static final List<LockWithId> locksSecond = new ArrayList<>();

    static {
        // Setup arrays in different order
        locksFirst.add(lockOne);
        locksFirst.add(lockTwo);
        locksSecond.add(lockTwo);
        locksSecond.add(lockOne);
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(getTaskLockResourcesFromLocksFirst());
        Thread threadTwo = new Thread(getTaskLockResourcesFromLocksSecond());
        threadOne.start();
        threadTwo.start();
    }

    private static Runnable getTaskLockResourcesFromLocksFirst() {
        return () -> {
            try {
                doTask(locksFirst);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    private static Runnable getTaskLockResourcesFromLocksSecond() {
        return () -> {
            try {
                doTask(locksSecond);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    private static void doTask(List<LockWithId> locks) throws InterruptedException {
        log("started");
        LockWithId lockOne = locks.get(0);
        LockWithId lockTwo = locks.get(1);
        if (lockOne.getLockId() > lockTwo.getLockId()) {
            lockOne = locks.get(1);
            lockTwo = locks.get(0);
        }
        log("try to get lockOne");
        synchronized (lockOne) {
            log("get lockOne");
            Thread.sleep(5000);
            log("try to get lockTwo");
            synchronized (lockTwo) {
                log("get lockTwo");
            }
        }
        log("release all locks");
    }

    private static void log(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }
}

class LockWithId {
    private final int lockId;

    public LockWithId(int lockId) {
        this.lockId = lockId;
    }

    public int getLockId() {
        return lockId;
    }
}
