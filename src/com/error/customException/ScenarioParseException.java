package com.error.customException;

public class ScenarioParseException extends Exception {
    public ScenarioParseException(String message) {
        super(message);
    }

    public ScenarioParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
