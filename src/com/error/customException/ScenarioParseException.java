package com.error.customException;

public class ScenarioParseException extends Exception {
    private final int lineNumber;
    private final String lineContent;

    public ScenarioParseException(String message) {
        super(message);
        this.lineNumber = -1;
        this.lineContent = null;
    }

    public ScenarioParseException(String message, Throwable cause) {
        super(message, cause);
        this.lineNumber = -1;
        this.lineContent = null;
    }

    public ScenarioParseException(String message, int lineNumber, String lineContent) {
        super(formatMessage(message, lineNumber, lineContent));
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
    }
    
    public ScenarioParseException(String message, int lineNumber, String lineContent, Throwable cause) {
        super(formatMessage(message, lineNumber, lineContent), cause);
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
    }

    private static String formatMessage(String message, int lineNumber, String lineContent) {
        return String.format("Line %d: %s\n> %s", lineNumber, message, lineContent);
    }
}
