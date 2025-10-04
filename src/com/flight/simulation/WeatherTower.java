package com.flight.simulation;

public class WeatherTower extends Tower {
    // for testing
    private static int instanceCount = 0;

    public WeatherTower() {
        instanceCount++;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        super.conditionsChanged();
    }

}
