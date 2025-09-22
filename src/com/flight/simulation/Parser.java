package com.flight.simulation;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;

public class Parser {
    public static ParsedData parseScenarioFile(String scenarioFile) throws IOException {
        try {
            if (!Files.exists(Paths.get(scenarioFile))) {
                throw new IOException("Scenario file not found: " + scenarioFile);
            }
        } catch (IOException e) {
            System.err.println("Error accessing the scenario file: " + e.getMessage());
            throw e;
        }

        List<String> fileLines = Files
                .lines(Paths.get(scenarioFile))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.toList());

        if (fileLines.isEmpty()) {
            System.err.println("Scenario file is empty or contains only blank lines.");
        }

        fileLines.forEach(System.out::println);
        int simulationSteps = 0;
        try {
            simulationSteps = Integer.parseInt(fileLines.get(0));

            System.out.println("Simulation Steps: " + simulationSteps);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format for simulation steps: " + fileLines.get(0));
        }

        List<AircraftData> aircraftDataList = new ArrayList<>();
        for (int i = 1; i < fileLines.size(); i++) {
            String[] parts = fileLines.get(i).split(" ");
            if (parts.length == 5) {
                String aircraftType = parts[0];
                String aircraftName = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                Coordinates coordinates = Coordinates.create(longitude, latitude, height);
                aircraftDataList.add(new AircraftData(aircraftType, aircraftName, coordinates));

                System.out.printf("Aircraft: %s %s at (%d, %d, %d)%n", aircraftType, aircraftName, longitude, latitude, height);
            } else {
                throw new IllegalArgumentException("Invalid line format at line " + (i + 1) + ": " + fileLines.get(i));
            }
        }
        return new ParsedData(simulationSteps, aircraftDataList);
    }
}
