package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec03_internationalization_localization;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class FormattingDatesAndTimes {

    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time1 = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);

        // Using format() method of Date/Time instances
        println(date1.format(DateTimeFormatter.ISO_LOCAL_DATE));
        println(time1.format(DateTimeFormatter.ISO_LOCAL_TIME));
        println(dateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // Using format() method of DateTimeFormatter instances
        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        println(shortDateTime.format(date1));
        println(shortDateTime.format(dateTime1));
        // print(shortDateTime.format(time1)); // UnsupportedTemporalTypeException

        // Using format() method of Date/Time instances with instances of DateTimeFormatter
        println(date1.format(shortDateTime));
        println(dateTime1.format(shortDateTime));
        // print(time1.format(shortDateTime)); // UnsupportedTemporalTypeException

        // Using SHORT and MEDIUM predefined format styles and custom formats
        DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter customF = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
        println(shortF.format(dateTime1));
        println(mediumF.format(dateTime1));
        println(customF.format(dateTime1));

        // Parsing Strings to Date/Time instances
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date2 = LocalDate.parse("01 02 2015", f); // Parse with custom formatter
        LocalTime time2 = LocalTime.parse("11:22");     // Parse with default formatter
        println(date2);
        println(time2);
    }
}
