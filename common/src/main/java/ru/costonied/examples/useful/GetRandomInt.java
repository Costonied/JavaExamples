package ru.costonied.examples.useful;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Get random int
 *
 * Origin link:
 * https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 */
public class GetRandomInt {

    /**
     * In Java 1.7 or later, the standard way to do this is as follows:
     *
     * @param min: begin of range
     * @param max: end of range
     * @return random integer in range (min:max)
     */
    public static int getRandomIntVariant1(int min, int max) {
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Before Java 1.7, the standard way to do this is as follows.
     *
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most = Integer.MAX_VALUE - 1
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int getRandomIntVariant2(int min, int max) {
        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }
}
