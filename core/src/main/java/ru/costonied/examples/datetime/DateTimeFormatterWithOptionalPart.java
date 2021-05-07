package ru.costonied.examples.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static ru.costonied.examples.logging.LoggerExample.info;

public class DateTimeFormatterWithOptionalPart {
    public static void main(String[] args) {
        String dateFull = "2021-04-05T14:57:19";
        String dateShort = "2021-05-05";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss]");

        info(LocalDate.parse(dateFull, formatter).getMonth());
        info(LocalDate.parse(dateShort, formatter).getMonth());
    }
}
