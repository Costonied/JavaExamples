package ru.costonied.examples.dates.JDK8;


import java.time.Instant;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * Simple example showing some work with Instant class.
 * The Instant class represents a specific moment in time in the GMT/UTC time zone.
 */
public class InstantExample {
    public static void main(String[] args) {

        /* Get current date and time using different ways */

        Instant instantNow = Instant.now();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();

        System.out.println("Instant now: " + instantNow);
        System.out.println("LocalDateTime now: " + localDateTimeNow);
        System.out.println("ZonedDateTime now: " + zonedDateTimeNow);


        /* Convert ZonedDateTime to Instant */

        Instant instantConvertedFromZoned = zonedDateTimeNow.toInstant();
        System.out.println("Instant converted from ZonedDateTime: " + instantConvertedFromZoned);


        /* Get duration between Instant objects */

        Instant instantLater = Instant.now();
        Duration duration = Duration.between(instantNow, instantLater);

        System.out.println("Duration between Instant objects in milliseconds: " + duration.toMillis());


        /* Allowed mathematics with Instant objects */

        Instant nextDay = instantNow.plus(1, ChronoUnit.DAYS);
        Instant nextHour = instantNow.plus(1, ChronoUnit.HOURS);

        System.out.println("Next day from now: " + nextDay);
        System.out.println("Next hour from now: " + nextHour);

        /* Not allowed mathematics with Instant objects */

        try {
            Instant nextYear = instantNow.plus(1, ChronoUnit.YEARS);
            Instant nextMonth = instantNow.plus(1, ChronoUnit.MONTHS);
        } catch (UnsupportedTemporalTypeException exception) {
            System.out.println("Unfortunately, you can't moth Instant with YEARS and MONTHS ");
        }

    }
}
