package ru.costonied.examples.concurrency.collections;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple example describing work with concurrent Hash Map implementations.
 * ConcurrentHashMap is thread-safe version of HashMap.
 */
public class ConcurrentHashMapExample {
    public static void main(String[] args) {

        // You could hide details of implementation from user and just give the Map interface.
        // Or you could give to user the ConcurrentMap interface to clear that it is concurrent implementation of Hash Map
        Map<String, Integer> map = new ConcurrentHashMap<>();

        map.put("One", 1);
        map.put("Two", 1);

        System.out.println(map.get("One"));

    }
}
