package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec03_internationalization_localization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class FormattingNumbers {

    public static void main(String[] args) throws ParseException {
        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;

        NumberFormat us = NumberFormat.getInstance(Locale.US);
        println(us.format(attendeesPerMonth));
        NumberFormat de = NumberFormat.getInstance(Locale.GERMANY);
        println(de.format(attendeesPerMonth));
        NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        println(ca.format(attendeesPerMonth));

        double price = 48;
        NumberFormat usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        println(usCurrency.format(price));

        // Parsing
        NumberFormat en = NumberFormat.getInstance(Locale.US);
        NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);

        String s = "40.45";
        println(en.parse(s));
        println(fr.parse(s));
        // print(en.parse("x85.3")); // Throws ParseException

        String amt = "$92,807.99";
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        double value = (Double) cf.parse(amt);
        println(value);
    }
}
