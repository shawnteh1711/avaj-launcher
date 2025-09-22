package com.flight.simulation;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    private Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
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

    public static Coordinates create(int longitude, int latitude, int height) {
        if (height > 100)
            throw new IllegalArgumentException("Height cannot be more than 100");
        else if (height < 0)
            throw new IllegalArgumentException("Height cannot be less than 0");
        return new Coordinates(longitude, latitude, height);
    }
}