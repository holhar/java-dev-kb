package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec01_dates_and_times;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class Durations {

    public static void main(String[] args) {

        // Creating Durations
        Duration daily = Duration.ofDays(1);
        Duration hourly = Duration.ofHours(1);
        Duration everyMinute = Duration.ofMinutes(1);
        Duration everyTenSeconds = Duration.ofSeconds(10);
        Duration everyMilli = Duration.ofMillis(1);
        Duration everyNano = Duration.ofNanos(1);

        // Duration includes another more generic factory method - it takes a number and a TemporalUnit
        Duration daily2 = Duration.of(1, ChronoUnit.DAYS);
        Duration hourly2 = Duration.of(1, ChronoUnit.HOURS);
        Duration everyMinute2 = Duration.of(1, ChronoUnit.MINUTES);
        Duration everyTenSeconds2 = Duration.of(1, ChronoUnit.SECONDS);
        Duration everyMilli2 = Duration.of(1, ChronoUnit.MILLIS);
        Duration everyNano2 = Duration.of(1, ChronoUnit.NANOS);

        // Applying with date and time objects
        LocalDate date1 = LocalDate.of(2015, 1, 20);
        LocalTime time1 = LocalTime.of(6, 15);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        Duration duration1 = Duration.ofHours(6);
        println(time1.minus(duration1));
        println(dateTime1.plus(duration1));
        // print(date1.plus(duration1));   // Throws UnsupportedTemporalTypeException

        // Day gets switched properly:
        LocalDate date2 = LocalDate.of(2015, 1, 20);
        LocalTime time2 = LocalTime.of(6, 15);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
        Duration duration2 = Duration.ofHours(23);
        println(time1.minus(duration1));
        println(dateTime1.plus(duration1));
        // print(date1.plus(duration1));   // Throws UnsupportedTemporalTypeException

        // Period and Duration are not equivalent
        LocalDate date3 = LocalDate.of(2015, 5, 25);
        Period period = Period.ofDays(1);
        Duration duration = Duration.ofHours(24);
        println(date3.plus(period));      // FINE
        println(date3.plus(duration));    // Unsupported unit: Seconds
    }
}
