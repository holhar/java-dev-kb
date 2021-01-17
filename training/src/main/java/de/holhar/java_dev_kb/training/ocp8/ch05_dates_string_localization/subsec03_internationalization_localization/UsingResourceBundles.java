package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec03_internationalization_localization;

import de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class UsingResourceBundles {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsingResourceBundles.class);

    public static void main(String[] args) {
        Locale us = new Locale("en", "US");
        Locale france = new Locale("fr", "FR");

        printProperties(us);
        println("");
        printProperties(france);

        // Retrieve values by setting a default as well
        Properties props = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", us);
        rb.keySet().stream().forEach(k -> props.put(k, rb.getString(k)));

        println(props.getProperty("notReallyAProperty"));
        println(props.getProperty("notReallyAProperty", "123"));

        /*
         * Determining which resource bundle to use
         */
        // there are two methods for getting a resource bundle
        //ResourceBundle.getBundle("name");                // <= uses default locale
        //ResourceBundle.getBundle("name", us);  // <= specify which locale to use

        // Example of how properties get resolved:
        Locale locale = new Locale("en", "CA");
        ResourceBundle rb1 = ResourceBundle.getBundle("Zoo", locale);
        LOGGER.debug(rb1.getString("hello"));
        LOGGER.debug(". ");
        LOGGER.debug(rb1.getString("name"));
        LOGGER.debug(" - ");
        LOGGER.debug(rb1.getString("open"));
        LOGGER.debug(" ");
        LOGGER.debug(rb1.getString("visitor"));
        LOGGER.debug(" ");
        println("");

        // Handling variable inside resource bundles
        ResourceBundle rb2 = ResourceBundle.getBundle("Zoo", new Locale("en"));
        String format = rb2.getString("helloByName");
        String formatted = MessageFormat.format(format, "Tammy");
        println(formatted);
    }

    private static void printProperties(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);

        // Get specific values
        //print(rb.getString("hello"));
        //print(rb.getString("open"));

        // Or iterate over all keys
        rb.keySet().stream().map(k -> k + " " + rb.getString(k)).forEach(OcpPrepUtils::println);
    }
}
