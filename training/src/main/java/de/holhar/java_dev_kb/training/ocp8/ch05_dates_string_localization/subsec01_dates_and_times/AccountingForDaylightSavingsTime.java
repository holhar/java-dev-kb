package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec01_dates_and_times;

import java.time.*;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class AccountingForDaylightSavingsTime {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time1 = LocalTime.of(1, 30);
        ZoneId zone1 = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(date1, time1, zone1);

        // Watch the application of daylight saving time
        println(zonedDateTime1);
        zonedDateTime1 = zonedDateTime1.plusHours(1);
        println(zonedDateTime1);

        // Creating a time that doesn't exist just rolls forward
        LocalDate date2 = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time2 = LocalTime.of(2, 30);
        ZoneId zone2 = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(date2, time2, zone2);
        println(zonedDateTime2);
    }
}
