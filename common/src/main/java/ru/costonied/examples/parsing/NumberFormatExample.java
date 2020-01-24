package ru.costonied.examples.parsing;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Example showing how to work with java.text.NumberFormat
 */
public class NumberFormatExample {

    private static Locale localeUS = Locale.US;
    private static Locale localeCF = Locale.CANADA_FRENCH;

    private static NumberFormat numberFormatUS = NumberFormat.getNumberInstance(localeUS);
    private static NumberFormat numberFormatCF = NumberFormat.getNumberInstance(localeCF);

    private static NumberFormat currencyFormatUS = NumberFormat.getCurrencyInstance(localeUS);

    public static void main(String[] args) {

        List<String> numbers = new ArrayList<>();
        numbers.add("234x");
        numbers.add("123");
        numbers.add("234.1");
        numbers.add("234,2");
        numbers.add("234,2.1");
        numbers.add("234 3");


        System.out.println("Parsing String to Number");

        System.out.println("<<< US Locale >>");
        parseNumberFromString(numbers, numberFormatUS);

        System.out.println("<<< Canadian France (CF) Locale >>");
        parseNumberFromString(numbers, numberFormatCF);

        System.out.println("Parsing integer to currency");
        parseIntToCurrency(10, currencyFormatUS);

    }

    /**
     * Parse each strings in a list and print it
     * @param list list of Stings where store numbers
     * @param numberFormat NumberFormat object in needed Locale
     */
    private static void parseNumberFromString(List<String> list, NumberFormat numberFormat) {
        // Using stream().forEach()
        list.forEach(n -> {
            try {
                System.out.println(numberFormat.parse(n));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Parse integer to currency.
     *
     * Note: in real world don't use float and double for monetary because you could lose precision.
     *       Use int or BigDecimal (the best idea)
     * @param i integer
     * @param currencyNumberFormat NumberFormat object received by getCurrencyInstance()
     */
    private static void parseIntToCurrency(int i, NumberFormat currencyNumberFormat) {
        System.out.println(currencyNumberFormat.format(i));
    }

}
