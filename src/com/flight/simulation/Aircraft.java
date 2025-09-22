package com.flight.simulation;

public abstract class Aircraft implements Flyable {
    private static long idCounter = 0;
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinates;
    }

    protected static long nextId() {
        return ++idCounter;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
        System.out.println("Tower says: " + this.name + "#" + this.id + " registered to weather tower.");
    }

}
