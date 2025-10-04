package com.flight.simulation;

public class AircraftFactory {
    private static AircraftFactory instance;
    private static int instanceCount = 0;

    private AircraftFactory() {
        instanceCount++;
    }

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
            instanceCount = 1;
        }
        return instance;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(Aircraft.nextId(), p_name, p_coordinates);
            case "jetplane":
                return new JetPlane(Aircraft.nextId(), p_name, p_coordinates);
            case "baloon":
                return new Baloon(Aircraft.nextId(), p_name, p_coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
        }
    }
}
