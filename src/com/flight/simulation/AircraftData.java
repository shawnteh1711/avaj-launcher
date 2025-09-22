package com.flight.simulation;

public class AircraftData {
    private final String type;
    private final String name;
    private final Coordinates coordinates;

    public AircraftData(String type, String name, Coordinates coordinates) {
        this.type = type;
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
