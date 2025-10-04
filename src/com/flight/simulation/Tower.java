package com.flight.simulation;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();
    protected Logger logger = Logger.getLogger();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        logger.log("Tower says: " + p_flyable.getAircraftInfo() + " registered to weather tower.");
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        logger.log("Tower says: " + p_flyable.getAircraftInfo() + " unregistered from weather tower.");
    }

    protected void conditionsChanged() {
        for (Flyable aircraft : new ArrayList<>(observers)) {
            aircraft.updateConditions();
        }
    }
}
