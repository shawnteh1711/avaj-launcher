package test.flight.simulation;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;

import java.io.IOException;

import com.error.customException.ScenarioParseException;
import com.flight.simulation.ParsedData;
import com.flight.simulation.Parser;

public class ParserTest {
    // Test cases would go here
    public static void runTests() {
        System.out.println("ParserTest class for testing parsing scenario file.");
        testParseValidScenarioFile();
        testParseFirstLineEmptyScenarioFile();
        testParseScenarioFileNotExist();
        testParseEmptyScenarioFile();
        testParseInvalidSimulationStepScenarioFile();
        testParseNegativeSimulationSteps();
        testParseInvalidParameterScenarioFile();
        testParseInvalidCoordinatesScenarioFile();
        testParseInvalidAircraftTypeScenarioFile();
        testParseInvalidAircaftName();
        testParseDecimalCoordinates();
        testParseNegativeCoordinates();
        testHeightMoreThan100();
    }

    private static void testParseValidScenarioFile() {
        String testFile = "test_scenario.txt";

        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            ParsedData data = Parser.parseScenarioFile(testFile);
            if (data.getSimulationSteps() != 5) {
                System.err.println("Test failed: Incorrect simulation steps");
            }
            if (data.getAircraftDataList().size() != 2) {
                System.err.println("Test failed: Incorrect number of aircraft");
            } else {
                System.out.println("Test passed: Valid scenario file parsed correctly");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ScenarioParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseScenarioFileNotExist() {
        String testFile = "non_existent_scenario.txt";
        try {
            Parser.parseScenarioFile(testFile);
            System.err.println("Test failed: Exception not thrown for non-existent file");
        } catch (ScenarioParseException e) {
            System.out.println("Test passed: Exception correctly thrown for non-existent file");
        }
    }

    private static void testParseEmptyScenarioFile() {
        String testFile = "empty_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList());
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for empty file");
            } catch (ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for empty file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseFirstLineEmptyScenarioFile() {
        String testFile = "empty_first_line_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "",
                    "5",
                    "JetPlane JP1 10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            ParsedData data = Parser.parseScenarioFile(testFile);
            if (data.getSimulationSteps() != 5) {
                System.err.println("Test failed: Incorrect simulation steps");
            }
            if (data.getAircraftDataList().size() != 2) {
                System.err.println("Test failed: Incorrect number of aircraft");
            } else {
                System.out.println("Test passed: Valid scenario file parsed correctly with empty first line");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ScenarioParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseInvalidSimulationStepScenarioFile() {
        String testFile = "invalid_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "invalid_number",
                    "JetPlane JP1 10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for invalid simulation steps");
            } catch (ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for invalid simulation steps");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseNegativeSimulationSteps() {
        String testFile = "negative_steps_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "-3",
                    "JetPlane JP1 10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for negative simulation steps");
            } catch (ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for negative simulation steps");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseInvalidParameterScenarioFile() {
        String testFile = "invalid_parameter_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 10 20 30 40",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for invalid parameter");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for invalid parameter");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseInvalidCoordinatesScenarioFile() {
        String testFile = "invalid_coordinates_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 10 -20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for invalid coordinates");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for invalid coordinates");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseInvalidAircraftTypeScenarioFile() {
        String testFile = "invalid_aircraft_type_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "UnknownType JP1 10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for invalid aircraft type");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for invalid aircraft type");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseInvalidAircaftName() {
        String testFile = "invalid_aircraft_name_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP@1 10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for invalid aircraft name");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for invalid aircraft name");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseNegativeCoordinates() {
        String testFile = "negative_coordinates_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 -10 20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for negative coordinates");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for negative coordinates");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testParseDecimalCoordinates() {
        String testFile = "non_integer_coordinates_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 10.5 20 30",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for non-integer coordinates");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for non-integer coordinates");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    private static void testHeightMoreThan100() {
        String testFile = "height_more_than_100_scenario.txt";
        try {
            Files.write(Paths.get(testFile), Arrays.asList(
                    "5",
                    "JetPlane JP1 10 20 150",
                    "Helicopter H1 15 25 35"
            ));
            try {
                Parser.parseScenarioFile(testFile);
                System.err.println("Test failed: Exception not thrown for height more than 100");
            } catch (IllegalArgumentException | ScenarioParseException e) {
                System.out.println("Test passed: Exception correctly thrown for height more than 100");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFile));
            } catch (IOException e) {
                // Ignore
            }
        }
    }
}