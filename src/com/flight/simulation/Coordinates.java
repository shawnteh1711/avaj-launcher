package com.flight.simulation;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;
    private static final int MAX_HEIGHT = 100;
    private static final int MIN_HEIGHT = 0;

    /* ~ means package private */
    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = enforceHeight(height);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    private static int enforceHeight(int height) {
        return Math.max(MIN_HEIGHT, Math.min(MAX_HEIGHT, height));
    }

    /* Returns a new Coordinates object with updated values and keep height at 100 */
    public Coordinates change(int deltaLongitude, int deltaLatitude, int deltaHeight) {
        if (deltaHeight > 0 && isAtMaxHeight()) {
            deltaHeight = 0;
        }
        if (this.height + deltaHeight <= MIN_HEIGHT) {
            return new Coordinates(this.longitude + deltaLongitude,
                                   this.latitude + deltaLatitude,
                                   MIN_HEIGHT);
        }
        return new Coordinates(this.longitude + deltaLongitude,
                               this.latitude + deltaLatitude,
                               this.height + deltaHeight);
    }

    public boolean hasLanded() {
        return this.height <= MIN_HEIGHT;
    }

    public boolean isAtMaxHeight() {
        return this.height == MAX_HEIGHT;
    }

    public String toString() {
        return "(" + this.longitude + ", " + this.latitude + ", " + this.height + ")";
    }
}