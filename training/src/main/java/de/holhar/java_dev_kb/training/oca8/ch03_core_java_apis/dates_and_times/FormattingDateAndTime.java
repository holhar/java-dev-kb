package de.holhar.java_dev_kb.training.oca8.ch03_core_java_apis.dates_and_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class FormattingDateAndTime {

    public static void main(String[] args) {

        print("Formatting Dates and Times");
        print("==========================");

        print("");
        print("Retrieving data out of date and time classes");
        print("--------------------------------------------");

        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        print(date1.getDayOfWeek());    // MONDAY
        print(date1.getMonth());    // JANUARY
        print(date1.getYear());        // 2020
        print(date1.getDayOfYear());    // 20

        LocalDate date2 = LocalDate.of(2017, Month.DECEMBER, 24);
        LocalTime time2 = LocalTime.of(11, 12, 13);
        LocalDateTime dateTime2 = LocalDateTime.of(date2, time2);

        print("");
        print("Usage of 'format()' method with ISO standard formats");
        print("----------------------------------------------------");

        print(date2.format(DateTimeFormatter.ISO_LOCAL_DATE));
        print(time2.format(DateTimeFormatter.ISO_LOCAL_TIME));
        print(dateTime2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        print("");
        print("Usage of 'DateTimeFormatter.ofLocalizedDate()'");
        print("----------------------------------------------");

        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        print(shortDateTime.format(dateTime2));
        print(shortDateTime.format(date2));
        // print(shortDateTime.format(time2)); // UnsupportedTemporalException

        print("");
        print("The order of DateTimeFormatter and date and time classes can be switched");
        print("------------------------------------------------------------------------");

        print(dateTime2.format(shortDateTime));
        print(date2.format(shortDateTime));
        // print(time2.format(shortDateTime)); // UnsupportedTemporalException

        print("");
        print("Difference between Using FormatStyle SHORT and MEDIUM");
        print("-----------------------------------------------------");

        DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        print(dateTime2.format(shortF));
        print(mediumF.format(dateTime2));

        print("");
        print("Using own formats with patterns");
        print("-------------------------------");

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
        print(dateTime2.format(f1));

        print("");
        print("Parsing Dates and Times using 'parse()' method");
        print("----------------------------------------------");

        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date3 = LocalDate.parse("01 02 2015", f2);
        LocalTime time3 = LocalTime.parse("11:22"); // If not passed a formatter, the method uses a default one
        print(date3);
        print(time3);
    }
}
