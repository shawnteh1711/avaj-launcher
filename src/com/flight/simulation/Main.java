package com.flight.simulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

import com.error.customException.ScenarioParseException;


public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].endsWith(".txt")) {
            System.err.println("Usage: java -cp com.flight.simulation.Main <scenario.txt>");
            System.exit(1);
        }

        try {
            ParsedData parsedData = Parser.parseScenarioFile(args[0]);
            Simulator.runSimulation(parsedData);
        } catch (ScenarioParseException e) {
            System.err.println("Error reading scenario file: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error data in scenario file: " + e.getMessage());
            System.exit(1);
        }
    }
}