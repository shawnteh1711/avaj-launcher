package com.flight.simulation;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
    private static Logger instance = null;
    private static final String OUTPUT_FILE = "simulation.txt";
    private static PrintWriter writer;

    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter(OUTPUT_FILE));
        } catch (Exception e) {
            System.err.println("Error initializing logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    public void close() {
        if (writer != null) {
            writer.close();
            writer = null;
            instance = null;
        }
    }
}
