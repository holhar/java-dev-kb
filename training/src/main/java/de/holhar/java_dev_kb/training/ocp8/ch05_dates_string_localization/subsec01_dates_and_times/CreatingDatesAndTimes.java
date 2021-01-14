package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec01_dates_and_times;

import de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils;

import java.time.*;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class CreatingDatesAndTimes {

    public static void main(String[] args) {

        // Get instances of now
        println(LocalDate.now());
        println(LocalTime.now());
        println(LocalDateTime.now());
        println(ZonedDateTime.now());

        // Create specific dates and times
        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);

        LocalTime time1 = LocalTime.of(6, 15);  // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30);	// + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds

        // Combine dates and times into one object
        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);

        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20, 6, 15, 30, 200, zone);
        ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
        ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);

        // Finding a time zone
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("US") || z.contains("America"))
                .sorted().forEach(OcpPrepUtils::println);

        // LocalDate date3 = new LocalDate(); // DOES NOT COMPILE
        try {
            LocalDate date4 = LocalDate.of(2019, Month.APRIL, 32);
        } catch (DateTimeException e) {
            println("Invalud numbers in of() method throws DateTimeException");
        }

    }
}
