package com.flight.simulation;

public class Simulator {

    public static void runSimulation(ParsedData parsedData) {
        WeatherTower weatherTower = new WeatherTower();
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        for (AircraftData data: parsedData.getAircraftDataList()) {
            try {
                Flyable flyable = aircraftFactory.newAircraft(data.getType(), data.getName(), data.getCoordinates());
                flyable.registerTower(weatherTower);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        for (int i = 0; i < parsedData.getSimulationSteps(); i++) {
            weatherTower.changeWeather();
        }
    }

    private static void createAircraft(ParsedData parsedData) {
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        for (AircraftData data : parsedData.getAircraftDataList()) {
            Flyable flyable = aircraftFactory.newAircraft(data.getType(), data.getName(), data.getCoordinates());
        }
    }
}
