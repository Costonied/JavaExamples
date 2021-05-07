package ru.costonied.examples.concurrency.managing;


import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import org.apache.commons.lang3.RandomUtils;

/**
 * Example describe how to work with RecursiveAction
 * tp applying fork/join framework.
 */
public class ForkJoinRecursiveActionExample {
    public static void main(String[] args) {

        int[] randomNumbers = new int[100];

        /*
            The three main steps of implementing fork/join framework:
              1. Create a ForkJoinTask
              2. Create the ForkJoinPool
              3. Start the ForkJoinTask
         */

        /* 1 - Create a ForkJoinTask */
        ForkJoinTask<?> task = new GetRandomNumberAction(0, randomNumbers.length, randomNumbers);

        /* 2 - Create the ForkJoinPool */
        ForkJoinPool pool = new ForkJoinPool();

        /* 3 - Start the ForkJoinTask */
        pool.invoke(task);

        System.out.println("");
        Arrays.stream(randomNumbers).forEach(System.out::println);

    }
}

/**
 * You have two options when implementing ForkJoinTask:
 *   1. Extends from RecursiveAction
 *   2. Extends from RecursiveTask
 * Here we describe the "1" option.
 */
class GetRandomNumberAction extends RecursiveAction {

    private int end;
    private int begin;
    private int[] randomNumbers;

    public GetRandomNumberAction(int begin, int end, int[] randomNumbers) {
        this.end = end;
        this.begin = begin;
        this.randomNumbers = randomNumbers;
    }

    @Override
    protected void compute() {
        if (end - begin <= 4) {
            for (int i = begin; i < end; i++) {
                randomNumbers[i] = RandomUtils.nextInt(1, 1000);
                System.out.println(String.format(
                        "Thread [%s] randomNumbers[%d] = %d",
                        Thread.currentThread().getName(), i, randomNumbers[i]));
            }
        } else {
            int middle = begin + (end - begin)/2;
            System.out.println(String.format(
                    "Action will be divided: begin[%d] : middle[%d] : end[%d]",
                    begin, middle, end));
            // invokeAll() method of super super class ForkJoinTask. It's forks the given tasks
            invokeAll(new GetRandomNumberAction(begin, middle, randomNumbers),
                      new GetRandomNumberAction(middle, end, randomNumbers));
        }
    }
}
