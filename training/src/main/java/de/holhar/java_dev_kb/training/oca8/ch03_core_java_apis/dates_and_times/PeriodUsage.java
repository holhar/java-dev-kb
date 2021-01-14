package de.holhar.java_dev_kb.training.oca8.ch03_core_java_apis.dates_and_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

/*
 * Period is for durations between years and days. For smaller duration there is the
 * class 'Duration', which is for durations between days and nanoseconds.
 */
public class PeriodUsage {

    public static void main(String[] args) {

        print("Usage of Period class");
        print("=====================");
        print("");
        print("Simple example");
        print("--------------");

        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        Period period = Period.ofMonths(1);
        performAnimalEnrichment(start, end, period);


        // Different ways of creating periods
        Period annually = Period.ofYears(1);
        Period quarterly = Period.ofMonths(3);
        Period everyThreeWeeks = Period.ofWeeks(3);
        Period everyOtherDay = Period.ofDays(2);
        Period everyYearAndWeek = Period.of(1, 0, 7);    // every year and 7 days

        // Method chaining does not work as the methods are static - only the last
        // chained method gets executed (The compiler signifies a WARN)
        Period wrong = Period.ofYears(1).ofWeeks(1);    // every week

        print("");
        print("Objects Period can be used with");
        print("-------------------------------");
        print("");

        LocalDate date2 = LocalDate.of(2015, 1, 2);
        LocalTime time2 = LocalTime.of(5, 15);
        LocalDateTime dateTime2 = LocalDateTime.of(date2, time2);
        Period period2 = Period.ofMonths(2);
        print(date2.plus(period2));            // works fine
        print(dateTime2.minus(period2));    // works fine
        print(time2.plus(period2));            // UnsupportedTemporalTypeException
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            print("give new toy: " + upTo);
            upTo = upTo.plus(period);
        }
    }
}
