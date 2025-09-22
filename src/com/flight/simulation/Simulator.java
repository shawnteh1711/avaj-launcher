package com.flight.simulation;

public class Simulator {

    public static void runSimulation(ParsedData parsedData) {
        WeatherTower weatherTower = new WeatherTower();



//        for (Flyable flyable : parsedData.getAircraftDataList()) {
//            flyable.registerTower(weatherTower);
//        }

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
