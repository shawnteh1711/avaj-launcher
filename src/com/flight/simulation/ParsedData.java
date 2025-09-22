package com.flight.simulation;

import java.util.List;

public class ParsedData {
    private final int simulationSteps;
    private final List<AircraftData> aircraftDataList;

    public ParsedData(int simulationSteps, List<AircraftData> aircraftDataList) {
        this.simulationSteps = simulationSteps;
        this.aircraftDataList = aircraftDataList;
    }

    public int getSimulationSteps() {
        return simulationSteps;
    }

    public List<AircraftData> getAircraftDataList() {
        return aircraftDataList;
    }
}
