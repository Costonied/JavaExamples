package ru.costonied.examples.io.serialization;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Dog dog = new Dog(2, "Layka");
        Cat cat = new Cat(3, "Yasha");
        File outputFileCat = new File("target/serialisedCat.txt");
        File outputFileDog = new File("target/serialisedDog.txt");

        serializeObjectToFile(cat, outputFileCat);
        serializeObjectToFile(dog, outputFileDog);

        Cat deserializedCat = deserializeObjectFromFile(Cat.class, outputFileCat);
        Dog deserializedDog = deserializeObjectFromFile(Dog.class, outputFileDog);

        System.out.println(deserializedCat);
        System.out.println(deserializedDog);

    }

    /**
     * Serialize any object to file
     * @param object Any object
     * @param outputFile destination file
     * @throws IOException If something was wrong
     */
    private static void serializeObjectToFile(Object object, File outputFile) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFile)))) {
            outputStream.writeObject(object);
        }
    }

    /**
     * Deserialize T object from file
     * @param type type of object which deserialized
     * @param inputFile file with serialized object
     * @param <T> type of object which deserialized
     * @return deserialized object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static <T> T deserializeObjectFromFile(Class<T> type, File inputFile) throws IOException,
                                                                                    ClassNotFoundException {
        Object deserializedObject;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(inputFile)))) {
            deserializedObject = objectInputStream.readObject();
        }

        if (type.isInstance(deserializedObject)) {
            return type.cast(deserializedObject);
        }

        return null;
    }

    /**
     * Inner class to serialize and deserialize
     */
    private static class Cat implements Serializable {

        // Parameter which help check that serialize and deserialize use the same version of object
        private static final long serialVersionUID = 1L;

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

    /**
     * Inner class to serialize and deserialize
     */
    private static class Dog implements Serializable {

        // Parameter which help check that serialize and deserialize use the same version of object
        private static final long serialVersionUID = 1L;

        private int age;
        private String name;

        public Dog(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Dog name is %s and age is %d",
                    this.name, this.age);
        }
    }

}
