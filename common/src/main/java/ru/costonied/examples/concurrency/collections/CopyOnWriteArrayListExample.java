package ru.costonied.examples.concurrency.collections;


import java.util.List;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Showing how to work with CopyOnWriteArrayList collection.
 */
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {

        List<Integer> originalList = Arrays.asList(1, 2, 3);
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(originalList);

        System.out.println("copyList size before loop = " + copyList.size());

        for (Integer integer : copyList) {
            // Thought we modified copyList object, loop cycle still work with first version while finish loop.
            // If we use simple ArrayList here then we will receive ConcurrentModificationException
            copyList.add(9);
            System.out.println("\tNext iteration: copyList size = " + copyList.size() + ", element = " + integer);
        }

        System.out.println("copyList size after loop = " + copyList.size());        // Have a new elements and size
        System.out.println("copyList elements is = " + copyList);
        System.out.println("originalList elements is = " + originalList);           // The original list was not changed

    }
}
