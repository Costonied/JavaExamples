package ru.costonied.examples.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Example - how to convert Date to String with needed format
 */
public class DateToString {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date currentDate = new Date();
        System.out.println("date: " + dateFormat.format(currentDate));
    }
}