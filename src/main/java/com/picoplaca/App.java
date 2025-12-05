package com.picoplaca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Predictor predictor = new Predictor();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("PICO Y PLACA PREDICTOR");

        try {
            System.out.print("Ingresa la placa (Ej: PDC-5423): ");
            String plateInput = scanner.nextLine();
            LicensePlate plate = new LicensePlate(plateInput);

            System.out.print("Ingresa la fecha (dd-MM-yyyy): ");
            String dateInput = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateInput, dateFormatter);

            System.out.print("Ingresa la hora (HH:mm, formato 24h): ");
            String timeInput = scanner.nextLine();
            LocalTime time = LocalTime.parse(timeInput, timeFormatter);

            boolean canCirculate = predictor.canCirculate(plate, date, time);

            System.out.println("Vehículo con placa: " + plate.getPlateNumber());
            System.out.println("Fecha: " + date + " | Hora: " + time);

            if (canCirculate) {
                System.out.println("PUEDE CIRCULAR.");
            } else {
                System.out.println("NO PUEDE CIRCULAR (Restricción Pico y Placa).");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error: Formato de fecha u hora incorrecto. Usa dd-MM-yyyy y HH:mm");
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}