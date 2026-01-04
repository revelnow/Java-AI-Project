package com.theo.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ChronoUnitDemo {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        Scanner bir = new Scanner(System.in);
        System.out.println("Enter your birthday:");
        String year = bir.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(year, formatter);

        long daysBetween = ChronoUnit.YEARS.between(birthday, date);
        System.out.println("Year lived: " + daysBetween);

    }
}
