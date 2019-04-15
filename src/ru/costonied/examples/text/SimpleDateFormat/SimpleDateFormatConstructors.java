package ru.costonied.examples.text.SimpleDateFormat;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Constructors using examples
 */

public class SimpleDateFormatConstructors {

    public static void main(String[] args) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat;

        dateFormat = new SimpleDateFormat();
        System.out.println("Constructor 1: " + dateFormat.format( currentDate ) );

        dateFormat = new SimpleDateFormat("dd MMMM");
        System.out.println("Constructor 2: " + dateFormat.format( currentDate ) );

        dateFormat = new SimpleDateFormat("dd MMMM", myDateFormatSymbols );
        System.out.println("Constructor 3: " + dateFormat.format( currentDate ) );

        dateFormat = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
        System.out.println("Constructor 4: " + dateFormat.format( currentDate ) );
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };
}