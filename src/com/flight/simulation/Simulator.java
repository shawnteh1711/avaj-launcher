package com.flight.simulation;

public class Simulator {

    private Simulator() {}

    public static void runSimulation(ParsedData parsedData) {
        WeatherTower weatherTower = new WeatherTower();

        for (AircraftData data: parsedData.getAircraftDataList()) {
            try {
                Flyable flyable = createAircraft(data);
                flyable.registerTower(weatherTower);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        for (int i = 0; i < parsedData.getSimulationSteps(); i++) {
            weatherTower.changeWeather();
        }
    }

    private static Flyable createAircraft(AircraftData data) {
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        return aircraftFactory.newAircraft(data.getType(), data.getName(), data.getCoordinates());
    }
}
