package test.flight.simulation;

import com.error.customException.ScenarioParseException;
import com.flight.simulation.AircraftFactory;
import com.flight.simulation.ParsedData;
import com.flight.simulation.Parser;
import com.flight.simulation.Simulator;
import com.flight.simulation.WeatherTower;

public class SimulatorTest {
    private static final String TESTFILE = "scenario.txt";

    public static void runTests() {
        System.out.println("SimulatorTest class for testing simulation");
        testCreatedWeatherTowerInstance();
    }

    private static void testCreatedWeatherTowerInstance() {

        try {
            ParsedData parsedData = Parser.parseScenarioFile(TESTFILE);
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
        }

    }
}
