package ru.costonied.examples.io.serialization;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;

/**
 * Show how to work with ObjectInputStream and ObjectOutputStream
 * while serialize object in file and deserialize it from file.
 */
public class ObjectInputOutputStreamExample {

    public static void main(String[] args) throws IOException {

        File outputFile = new File("/target/serialisedCat.txt");
        Cat cat = new Cat(3, "Yasha");

        serializeCatToFile(cat, outputFile);

    }

    private static void serializeCatToFile(Cat cat, File outputFile) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFile)))) {
            outputStream.writeObject(cat);
        }
    }

    /**
     * Inner class to serialize and deserialize
     */
    private static class Cat implements Serializable {

        private int age;
        private String name;

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Cat name is %s and age is %d",
                    this.name, this.age);
        }
    }

}
