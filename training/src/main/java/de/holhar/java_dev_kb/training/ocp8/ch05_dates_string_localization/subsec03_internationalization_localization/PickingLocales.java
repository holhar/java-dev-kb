package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec03_internationalization_localization;

import java.util.Locale;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class PickingLocales {

    public static void main(String[] args) {

        Locale locale = Locale.getDefault();
        println(locale);

        // Getting Locales via constants
        println(Locale.GERMAN);   // de
        println(Locale.GERMANY);  // de_DE

        // Create new Locales
        println(new Locale("fr"));                // fr
        println(new Locale("hi", "IN"));  // hi_IN

        // Create Locales via builder
        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        Locale l2 = new Locale.Builder()
                .setRegion("US")
                .setLanguage("en")
                .build();

        // Set new default Locale
        println(Locale.getDefault());
        Locale locale1 = new Locale("fr");
        Locale.setDefault(locale1);
        println(Locale.getDefault());
    }
}
