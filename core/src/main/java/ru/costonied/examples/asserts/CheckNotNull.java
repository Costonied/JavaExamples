package ru.costonied.examples.asserts;

import java.util.Objects;

/**
 * Example show how to check null reference using java core API.
 * Use java.util.Objects.requireNonNull(Object object)
 */
public class CheckNotNull {

    public static void main(String[] args) {

        Object obj_1 = null;
        Object obj_2 = new Object();

        try {
            obj_1 = Objects.requireNonNull(obj_1);
        } catch (NullPointerException e) {
            System.out.println("OBJ_1 - NullPointerException was caught!");
        }

        obj_2 = Objects.requireNonNull(obj_2);
        System.out.println("OBJ_2 - jbj_2 is not null");

    }
}
