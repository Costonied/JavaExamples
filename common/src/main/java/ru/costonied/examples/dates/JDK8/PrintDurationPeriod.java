package ru.costonied.examples.dates.JDK8;


import java.time.Period;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Example showing how looks Duration and Periods when you print it
 */
public class PrintDurationPeriod {
    public static void main(String[] args) {

        String durationMinutes1 = Duration.of(1, ChronoUnit.MINUTES).toString();
        String durationSeconds1 = Duration.of(1, ChronoUnit.SECONDS).toString();
        String durationSeconds60 = Duration.of(60, ChronoUnit.SECONDS).toString();

        String periodDays1 = Period.ofDays(1).toString();
        String periodDays365 = Period.ofDays(365).toString();
        String periodYears1 = Period.ofYears(1).toString();
        String periodMonths1 = Period.ofMonths(1).toString();

        System.out.println(durationMinutes1);       // PT1M
        System.out.println(durationSeconds1);       // PT1S
        System.out.println(durationSeconds60);      // PT1M - shows like not 60 seconds but 1 minute

        System.out.println(periodDays1);            // P1D
        System.out.println(periodDays365);          // P365D
        System.out.println(periodYears1);           // P1Y
        System.out.println(periodMonths1);          // P1M

    }
}
