package ru.costonied.examples.io.serialization;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Example using of modifier "transient".
 *
 * Serialization doesn't save variable "InputStream in",
 * deserialization doesn't load the variable.
 */
public class TransientExample {

    class Cat implements Serializable
    {
        public String name;
        public int age;
        public int weight;

        transient public InputStream in = System.in;
    }
}