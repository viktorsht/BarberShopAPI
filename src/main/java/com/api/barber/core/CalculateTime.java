package com.api.barber.core;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalculateTime {
    public static boolean isTimeDifferenceGreaterThan2Hours(String strSpecificTime) {
        // Defining the custom date and time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Parsing string representation to LocalDateTime
        LocalDateTime specificTime = LocalDateTime.parse(strSpecificTime, formatter);

        // Getting the current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Calculating the difference in hours
        long hoursDifference = ChronoUnit.HOURS.between(currentTime, specificTime);

        // Returning true if the difference is greater than or equal to 2 hours, false otherwise
        return Math.abs(hoursDifference) >= 2;
    }

    public static boolean isAfterCurrentTime(LocalTime inputTime) {
        try {
            LocalTime currentTime = LocalTime.now();
            //LocalTime providedTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

            return inputTime.isAfter(currentTime);
        } catch (Exception e) {
            // Se houver uma exceção ao analisar a hora, retorna false (ou faça o tratamento apropriado)
            return false;
        }
    }
}
