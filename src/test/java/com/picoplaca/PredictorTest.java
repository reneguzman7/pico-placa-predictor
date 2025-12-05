package com.picoplaca;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

class PredictorTest {

    private final Predictor predictor = new Predictor();

    @Test
    void testRestrictedMondayMorning() {
        LicensePlate plate = new LicensePlate("PBA-3132");
        LocalDate monday = LocalDate.of(2025, 12, 1);
        LocalTime morningPeak = LocalTime.of(8, 0);

        boolean result = predictor.canCirculate(plate, monday, morningPeak);

        assertFalse("La placa terminada en 1 NO debería circular el lunes a las 8:00 AM", result);
    }

    @Test
    void testAllowedMondayNight() {
        LicensePlate plate = new LicensePlate("PDN-7854");
        LocalDate monday = LocalDate.of(2025, 12, 1);
        LocalTime night = LocalTime.of(21, 0);

        boolean result = predictor.canCirculate(plate, monday, night);

        assertTrue("Debería circular el lunes por la noche", result);
    }

    @Test
    void testWeekendFreeCirculation() {
        LicensePlate plate = new LicensePlate("PCK-2654");
        LocalDate saturday = LocalDate.of(2025, 12, 6);
        LocalTime anyTime = LocalTime.of(8, 0);

        assertTrue(predictor.canCirculate(plate, saturday, anyTime));
    }

    @Test
    void testEdgeCaseEndOfMorningPeak() {
        LicensePlate plate = new LicensePlate("PBC-9872");
        LocalDate monday = LocalDate.of(2025, 12, 1);
        LocalTime edgeTime = LocalTime.of(9, 30);

        assertFalse(predictor.canCirculate(plate, monday, edgeTime));
    }
}