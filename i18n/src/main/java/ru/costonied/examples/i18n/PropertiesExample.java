package ru.costonied.examples.i18n;


import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Example show how to work with Properties instead ResourceBundle
 */
public class PropertiesExample {
    public static void main(String[] args) {

        // Set locales or get from default
        Locale localeRu = Locale.getDefault();        // In Russia it will be "ru_RU"

        // Get ResourceBundle for locale
        ResourceBundle resourceBundleRu = ResourceBundle.getBundle("I18N", localeRu);

        // Create Properties object and move there our props from ResourceBundle
        Properties properties = new Properties();
        resourceBundleRu.keySet().stream()
                .forEach(k -> properties.put(k, resourceBundleRu.getString(k)));

        System.out.println(properties.getProperty("country"));
        System.out.println(properties.getProperty("noSuchProp"));       // print null
        System.out.println(properties.getProperty("noSuchProp", "default"));
        System.out.println(properties.getProperty("country", "also default"));

    }
}
