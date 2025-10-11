package test.flight.simulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.error.customException.ScenarioParseException;
import com.flight.simulation.AircraftFactory;
import com.flight.simulation.ParsedData;
import com.flight.simulation.Parser;
import com.flight.simulation.Simulator;
import com.flight.simulation.WeatherTower;

public class SimulatorTest {

    public static void runTests() {
        System.out.println("SimulatorTest class for testing simulation");
        testInstanceCreated();
    }

    private static void testInstanceCreated() {
        String testFile = "test_scenario.txt";

        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 10 20 30",
                    "Helicopter H1 15 25 35",
                    "Baloon B1 10 20 30"
            ));
            ParsedData parsedData = Parser.parseScenarioFile(testFile);
            Simulator.runSimulation(parsedData);

            if (WeatherTower.getInstanceCount() == 1 && AircraftFactory.getInstanceCount() == 1) {
                System.out.println("Test Passed: Only one instance of WeatherTower and AircraftFactory created.");
            } else {
                System.out.println("Test Failed: Multiple instances of WeatherTower or AircraftFactory detected.");
            }

        } catch (ScenarioParseException e) {
            System.out.println("Error during test: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during test: " + e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }

    }
}
