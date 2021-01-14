package de.holhar.java_dev_kb.training.oca8.ch03_core_java_apis.dates_and_times;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Oca8DateTimeFormatter {

    public static void main(String[] args) {
        DateTimeFormatter d1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2057-01-29", d1);

        DateTimeFormatter d2 = DateTimeFormatter.ofPattern("yyyy");
        LocalDate date2 = LocalDate.parse("2057", d2);
    }
}
