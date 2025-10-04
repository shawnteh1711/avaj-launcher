package com.flight.simulation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;

import com.error.customException.ScenarioParseException;

public class Parser {

    private Parser() {}

    public static ParsedData parseScenarioFile(String scenarioFile) throws ScenarioParseException {
        getScenarioFilePathOrThrow(scenarioFile);

        List<String> fileLines = parseScenarioFileLines(scenarioFile);

        int simulationSteps = parseSimulationSteps(fileLines.get(0));

        List<AircraftData> aircraftDataList = new ArrayList<>();
        for (int i = 1; i < fileLines.size(); i++) {
            aircraftDataList.add(parseAircraftData(fileLines.get(i), i + 1));
        }
        return new ParsedData(simulationSteps, aircraftDataList);
    }

    private static List<String> parseScenarioFileLines(String scenarioFile) throws ScenarioParseException {
        List<String> fileLines;

        try {
            fileLines = Files
                    .lines(Paths.get(scenarioFile))
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ScenarioParseException("Failed to read scenario file: " + scenarioFile, e);
        }

        if (fileLines.isEmpty()) {
            throw new ScenarioParseException("Scenario file is empty or contains only blank lines.");
        }
        return fileLines;
    }

    private static AircraftData parseAircraftData(String line, int lineNumber) throws ScenarioParseException {
        String[] parts = line.split(" ");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line format at line " + lineNumber + ": " + line);
        }

        String aircraftType = parts[0];
        String aircraftName = parts[1];
        int longitude;
        int latitude;
        int height;

        try {
            longitude = Integer.parseInt(parts[2]);
            latitude = Integer.parseInt(parts[3]);
            height = Integer.parseInt(parts[4]);
        } catch (NumberFormatException e) {
            throw new ScenarioParseException("Invalid coordinate format at line " + lineNumber + ": " + line, e);
        }
        validateAircraftType(aircraftType, lineNumber, line);
        validateAircraftName(aircraftName, lineNumber, line);
        validateCoordinates(longitude, latitude, height, lineNumber, line);
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        return new AircraftData(aircraftType, aircraftName, coordinates);
    }

    private static Path getScenarioFilePathOrThrow(String scenarioFile) throws ScenarioParseException {
        Path path = Paths.get(scenarioFile);
        if (!Files.exists(path)) {
            throw new ScenarioParseException("Scenario file not found: " + scenarioFile);
        }
        if (!Files.isReadable(path)) {
            throw new ScenarioParseException("Scenario file is not readable: " + scenarioFile);
        }
        return path;
    }

    private static int parseSimulationSteps(String line) throws ScenarioParseException {
        try {
            int steps = Integer.parseInt(line);
            if (steps <= 0) {
                throw new ScenarioParseException("Simulation steps must be a positive integer.");
            }
            return steps;
        } catch (NumberFormatException e) {
            throw new ScenarioParseException("Invalid number format for simulation steps: " + line, e);
        }
    }

    private static void validateAircraftType(String aircraftType, int lineNumber, String line) throws ScenarioParseException {
        if (!aircraftType.equals("Helicopter") && !aircraftType.equals("JetPlane") && !aircraftType.equals("Baloon")) {
            throw new ScenarioParseException("Invalid aircraft type at line " + lineNumber + ": " + line);
        }
    }

    private static void validateAircraftName(String aircraftName, int lineNumber, String line) throws ScenarioParseException{
        if (!aircraftName.matches("[a-zA-Z0-9_]+")) {
            throw new ScenarioParseException("Invalid aircraft name at line " + lineNumber + ": " + line);
        }
    }

    private static void validateCoordinates(int longitude, int latitude, int height, int lineNumber, String line) throws ScenarioParseException {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new ScenarioParseException("Negative coordinate value at line " + lineNumber + ": " + line);
        }
        if (height > 100) {
            throw new ScenarioParseException("Height exceeds maximum limit of 100 at line " + lineNumber + ": " + line);
        }
    }
}
