package ru.costonied.examples.i18n;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Simple example shows how to do internationalization (i18n) using Java Resource Bundle files
 */
public class ResourceBundleExample {
    public static void main(String[] args) {

        // Set several locales
        Locale localeRu = Locale.getDefault();        // In Russia it "ru_RU"
        Locale localeEn = new Locale("en");  // Just "en" without country is also valid value

        // Get ResourceBundle for locales
        ResourceBundle resourceBundleRu = ResourceBundle.getBundle("I18N", localeRu);
        ResourceBundle resourceBundleEn = ResourceBundle.getBundle("I18N", localeEn);

        String patternString = "Country is %s. City is %s";
        String stringInRu = String.format(patternString, resourceBundleRu.getString("country"), resourceBundleRu.getString("city"));
        String stringInEn = String.format(patternString, resourceBundleEn.getString("country"), resourceBundleEn.getString("city"));

        System.out.println(stringInRu);
        System.out.println(stringInEn);

        // Showing how to get values from parent if specific bundle haven't a key
        System.out.println(resourceBundleEn.getString("time"));     // default_time
        System.out.println(resourceBundleEn.getString("river"));    // default_river

    }
}
