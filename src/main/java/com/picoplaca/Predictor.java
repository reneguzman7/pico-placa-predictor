package com.picoplaca;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Predictor {

    private static final LocalTime MORNING_START = LocalTime.of(7, 0);
    private static final LocalTime MORNING_END = LocalTime.of(9, 30);
    private static final LocalTime AFTERNOON_START = LocalTime.of(16, 0);
    private static final LocalTime AFTERNOON_END = LocalTime.of(19, 30);


    public boolean canCirculate(LicensePlate plate, LocalDate date, LocalTime time) {
        DayOfWeek day = date.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return true;
        }

        if (!isPeakHour(time)) {
            return true;
        }

        int lastDigit = plate.getLastDigit();
        return !isRestrictedOnDay(lastDigit, day);
    }

    private boolean isPeakHour(LocalTime time) {
        boolean isMorningPeak = !time.isBefore(MORNING_START) && !time.isAfter(MORNING_END);
        boolean isAfternoonPeak = !time.isBefore(AFTERNOON_START) && !time.isAfter(AFTERNOON_END);

        return isMorningPeak || isAfternoonPeak;
    }

    private boolean isRestrictedOnDay(int lastDigit, DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return lastDigit == 1 || lastDigit == 2;
            case TUESDAY:
                return lastDigit == 3 || lastDigit == 4;
            case WEDNESDAY:
                return lastDigit == 5 || lastDigit == 6;
            case THURSDAY:
                return lastDigit == 7 || lastDigit == 8;
            case FRIDAY:
                return lastDigit == 9 || lastDigit == 0;
            default:
                return false;
        }
    }
}