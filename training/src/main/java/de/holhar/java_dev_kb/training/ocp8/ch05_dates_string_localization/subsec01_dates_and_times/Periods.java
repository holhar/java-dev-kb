package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec01_dates_and_times;

import java.time.*;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class Periods {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        performAnimalEnrichment(start, end);

        Period period = Period.ofMonths(1);
        performAnimalEnrichment(start, end, period);

        // Creating periods
        Period annually = Period.ofYears(1);
        Period quarterly = Period.ofMonths(3);
        Period everyThreeWeeks = Period.ofWeeks(3);
        Period everyOtherDay = Period.ofDays(2);
        Period everyYearAndWeek = Period.of(1, 0, 7);   // Week not specified (only through days)

        // static methods for creating a Period can not be chained:
        Period wrong = Period.ofYears(1).ofWeeks(3);    // EVERY THREE WEEKS

        // Period format: P1Y2M3D
        println(Period.of(1, 2, 3));
        println(Period.of(0, 20, 47));
        println(Period.ofWeeks(3));   // prints 21D!!!

        // Objects that can be used with Periods
        LocalDate date = LocalDate.of(2015, 1, 20);
        LocalTime time = LocalTime.of(6, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Period period2 = Period.ofMonths(1);
        println(date.minus(period));
        println(dateTime.plus(period));
        println(time.plus(period));   // UnsupportedTemporalTypeException
    }

    /*
     * Version without Period - inflexible!
     */
    private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            println("Give new toy: " + upTo);
            upTo = upTo.plusMonths(1);
        }
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            println("Give new toy: " + upTo);
            upTo = upTo.plus(period);
        }
    }
}
