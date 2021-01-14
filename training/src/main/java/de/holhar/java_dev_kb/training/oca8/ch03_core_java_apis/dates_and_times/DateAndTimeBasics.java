package de.holhar.java_dev_kb.training.oca8.ch03_core_java_apis.dates_and_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class DateAndTimeBasics {

    public static void main(String[] args) {
        print("");
        print("Working with Dates and Times");
        print("============================");
        print("");

        print("Creating Date, Time, and DateTime objects");
        print("-----------------------------------------");
        print("");

        // The date/time classes only have private constructors to enforce the
        // usage of the static methods - following DOES NOT COMPILE:
        // LocalDate d = new LocalDate();

        // Each of the three date/time classes has a static method called now()
        print(LocalDate.now());
        print(LocalTime.now());
        print(LocalDateTime.now());

        // The month can be declared by using an enum or ints
        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);

        // Time can be created in different detail
        LocalTime time1 = LocalTime.of(6, 15);            // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30);        // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds

        // DateTime can be created like shown above by combining the shown rules
        // or by providing date and time objects
        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 16, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);

        // Passing invalid numbers to '.of()' does compile, but WILL THROW AN EXCEPTION
        // LocalDate.of(2015, Month.JANUARY, 37);

        print("");
        print("Manipulating Date, Time, and DateTime objects");
        print("---------------------------------------------");

        // The date and time classes are immutable, just like 'String' => assign the
        // results of these methods to a reference variable so they are not lost.

        print("");
        print("Adding values to a date");
        print("");

        LocalDate date3 = LocalDate.of(2012, Month.FEBRUARY, 19);
        print(date3);
        date3 = date3.plusDays(7);
        print(date3);
        date3 = date3.plusWeeks(6);
        print(date3);
        date3 = date3.plusMonths(4);
        print(date3);
        date3 = date3.plusYears(1);
        print(date3);

        print("");
        print("Subtracting values from a dateTime object");
        print("");

        LocalDate date4 = LocalDate.of(2015, Month.FEBRUARY, 23);
        LocalTime time4 = LocalTime.of(11, 11);
        LocalDateTime dateTime3 = LocalDateTime.of(date4, time4);
        print(dateTime3);
        dateTime3 = dateTime3.minusDays(5);
        print(dateTime3);
        dateTime3 = dateTime3.minusHours(1);
        print(dateTime3);
        dateTime3 = dateTime3.minusMinutes(11);
        print(dateTime3);

        // The methods for adding and subtracting date/time values can also be chained
        dateTime3 = dateTime3.plusWeeks(2).minusDays(1).plusMonths(10).minusYears(50);
        print(dateTime3);

        // Adding or subtracting values without reassignment has no effect, as the
        // date and time classes are immutable
        dateTime3.plusYears(15);
        print(dateTime3);

        // You can not add or subtract something that does not belong to the respective
        // date or time object, the following DOES NOT COMPILE
        // date4.plusSeconds(45);
    }
}
