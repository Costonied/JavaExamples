package ru.costonied.examples.string;

import java.util.Collections;

/**
 * Examples how you could easy repeat strings in different versions of Java
 */
public class StringRepeat {
    public static void main(String[] args) {
    /*
     *  Java <= 7
     *  String.format("%0" + n + "d", 0).replace("0", s);
     */

    /*
     *  Java > 8
     */
        int n = 5;
        String source = "Source string";
        String result = String.join("", Collections.nCopies(n, source));
        System.out.println(result);

    /*
     *  Java 11
     *  int n = 3;
     *  "abc".repeat(n);
     */
    }
}
