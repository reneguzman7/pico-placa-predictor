package com.picoplaca;

import java.util.regex.Pattern;

public class LicensePlate {

    private final String plateNumber;

    private static final Pattern PLATE_PATTERN = Pattern.compile("^[A-Z]{3}-\\d{3,4}$");

    public LicensePlate(String plateNumber) {
        if(plateNumber == null)
            throw new IllegalArgumentException("La placa no puede ser nula");

        String cleanedPlate = plateNumber.trim().toUpperCase();
        if(!PLATE_PATTERN.matcher(cleanedPlate).matches()) {
            throw new IllegalArgumentException("Formato de placa invalido. Debe ser AAA-1234 o AAA-123");
        }
        this.plateNumber = cleanedPlate;
    }
    public String getPlateNumber() {
        return plateNumber;
    }

    public int getLastDigit() {
        char lastChar = plateNumber.charAt(plateNumber.length() - 1);
        return Character.getNumericValue(lastChar);
    }

}
