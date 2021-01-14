package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec01_dates_and_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ManipulatingDatesAndTimes {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2014, Month.JANUARY, 20);
        println(date1);
        date1 = date1.plusDays(2);
        println(date1);
        date1 = date1.plusWeeks(1);
        println(date1);
        date1 = date1.plusMonths(1);
        println(date1);
        date1 = date1.plusYears(5);
        println(date1);

        LocalTime time1 = LocalTime.of(5, 15);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        println(dateTime1);
        dateTime1 = dateTime1.minusDays(10);
        println(dateTime1);
        dateTime1 = dateTime1.minusHours(2);
        println(dateTime1);
        dateTime1 = dateTime1.minusMinutes(10);
        println(dateTime1);
        dateTime1 = dateTime1.minusSeconds(30);
        println(dateTime1);

        // Manipulations like this can be chained
        dateTime1 = dateTime1.minusDays(1).minusHours(10).minusSeconds(30);
        println(dateTime1);
    }
}
