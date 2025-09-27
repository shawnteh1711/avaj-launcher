package com.flight.simulation;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider instance = null;

    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        Random rand = new Random();
        return weather[rand.nextInt(weather.length)];
    }
}
