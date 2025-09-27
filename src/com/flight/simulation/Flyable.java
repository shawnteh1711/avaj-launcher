package com.flight.simulation;

public interface Flyable {

    public abstract void updateConditions();

    public abstract void registerTower(WeatherTower p_tower);

    public abstract String getAircraftInfo();
}
