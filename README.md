# Predictor Pico y Placa

## Descripción General

**Predictor Pico y Placa** es una aplicación Java que predice si un vehículo puede circular en una fecha y hora específica basándose en el sistema de restricción vehicular "Pico y Placa".

## Características

- **Validación de placas vehiculares**: Verifica que el formato de la placa sea válido (ej: PDC-5423)
- **Cálculo de restricciones**: Determina si un vehículo puede circular según el último dígito de su placa y el día de la semana
- **Horarios de restricción**: Valida si la hora consultada corresponde a horas pico (mañana: 7:00-9:30, tarde: 16:00-19:30)
- **Exención de fines de semana**: Los vehículos pueden circular libremente los sábados y domingos
- **Interfaz interactiva**: Aplicación de línea de comandos fácil de usar

## Reglas de Restricción

El sistema "Pico y Placa" funciona según el último dígito de la placa:

| Último Dígito | Día Restringido |
|---|---|
| 1, 2 | Lunes |
| 3, 4 | Martes |
| 5, 6 | Miércoles |
| 7, 8 | Jueves |
| 9, 0 | Viernes |

**Horarios de restricción:**
- **Mañana**: 07:00 - 09:30
- **Tarde**: 16:00 - 19:30

## Descripción de Clases

### `LicensePlate`
Representa una placa vehicular y encapsula su validación.
- **Responsabilidad**: Validar formato y extraer el último dígito
- **Métodos principales**:
  - `LicensePlate(String plateNumber)`: Constructor con validación
  - `getPlateNumber()`: Retorna el número de placa
  - `getLastDigit()`: Retorna el último dígito de la placa

### `Predictor`
Contiene la lógica principal de predicción de circulación.
- **Responsabilidad**: Determinar si un vehículo puede circular
- **Métodos principales**:
  - `canCirculate(LicensePlate, LocalDate, LocalTime)`: Indica si puede circular
  - `isPeakHour(LocalTime)`: Verifica si es hora pico
  - `isRestrictedOnDay(int, DayOfWeek)`: Valida restricción por día

### `App`
Interfaz de usuario interactiva.
- **Responsabilidad**: Interactuar con el usuario y mostrar resultados
- **Funcionalidades**:
  - Solicita entrada de placa, fecha y hora
  - Maneja excepciones y formatos de entrada
  - Muestra el resultado de la predicción

## Pruebas Unitarias

El proyecto incluye pruebas automatizadas con JUnit 5 que cubren:

- Restricción en día de semana durante hora pico
- Circulación permitida fuera de horas pico
- Circulación libre los fines de semana
- Casos límite (bordes de horarios de restricción)



