package ru.costonied.examples.concurrency.collections;


import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The simple example showing that ConcurrentLinkedQueue is like a LinkedQueue
 * but you should use it for multi-thread programming.
 */
public class ConcurrentLinkedQueueExample {
    public static void main(String[] args) {

        Queue<String> queue = new ConcurrentLinkedQueue<>();

        // Inserts the specified element into this queue
        // if it is possible to do so immediately without violating capacity restrictions,
        // returning true upon success
        // and throwing an IllegalStateException if no space is currently available.
        queue.add("One");

        // Inserts the specified element into this queue
        // if it is possible to do so immediately without violating capacity restrictions.
        // When using a capacity-restricted queue, this method is generally preferable to add.
        // Return true if the element was added to this queue, else false   
        queue.offer("Two");

        // peek() Retrieves, but does not remove, the head of this queue,
        // or returns null if this queue is empty.
        // So below you will see print of "One" element twice.
        System.out.println(queue.peek());
        System.out.println(queue.peek());

        // element() Retrieves, but does not remove, the head of this queue.
        // This method differs from peek only in that it throws an exception if this queue is empty.
        // So below you will see print of "One" element twice.
        System.out.println(queue.element());
        System.out.println(queue.element());

        // Retrieves and removes the head of this queue, or returns null if this queue is empty.
        // You'll see "One"
        System.out.println(queue.poll());

        // Retrieves and removes the head of this queue.
        // This method differs from poll only in that it throws an exception if this queue is empty.
        // You'll see "Two"
        System.out.println(queue.remove());

        try {
            queue.remove();
        } catch (NoSuchElementException e) {
            System.out.println("No one element inside the queue!");
        }

    }
}
