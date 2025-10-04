package com.flight.simulation;

public abstract class Aircraft implements Flyable {
    private static long idCounter = 0;
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;
    protected Logger logger = Logger.getLogger();

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinates;
    }

    protected static long nextId() {
        return ++idCounter;
    }

    protected void updatePosition(Coordinates newCoordinates) {
        if (this.coordinates.hasLanded()) {
            return;
        }

        if (!this.coordinates.hasLanded() && newCoordinates.hasLanded()) {
            logger.log(this.getAircraftInfo() + " landing.");
            this.weatherTower.unregister(this);
        }
        this.coordinates = newCoordinates;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }

    @Override
    public String getAircraftInfo() {
        return this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ")";
    }

    public void logMessage(String message) {
        logger.log(this.getAircraftInfo() + ": " + message);
    }

}
