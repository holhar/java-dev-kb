package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec01_dates_and_times;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class Instants {

    public static void main(String[] args) throws InterruptedException {

        // The Instant class represents a specific moment in time in the GMT time zone
        Instant now = Instant.now();
        Thread.sleep(1250);
        Instant later = Instant.now();

        Duration duration = Duration.between(now, later);
        println(duration);

        // Turn a ZonedDateTime into an Instant
        LocalDate date = LocalDate.of(2015, 5, 25);
        LocalTime time = LocalTime.of(11, 55, 00);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zone);
        println(zonedDateTime);
        Instant instant = zonedDateTime.toInstant();
        println(instant);

        // If you have the number of seconds since 1970, you can create an Instant that way:
        Instant instant1 = Instant.ofEpochSecond(1_000_000_000);
        println(instant1);

        // Doing math with instant (beginning from day and smaller)
        Instant nextDay = instant1.plus(1, ChronoUnit.DAYS);
        println(nextDay);
        Instant nextHour = instant1.plus(1, ChronoUnit.HOURS);
        println(nextHour);
        Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS); // Throws exception
    }
}
