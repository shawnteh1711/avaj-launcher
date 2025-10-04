package com.flight.simulation;

public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        Coordinates newCoordinates = this.coordinates;

        switch (weather) {
            case "SUN":
                newCoordinates = this.coordinates.change(2, 0, 4);
                logger.log(this.getAircraftInfo() + ": It's sunny. Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                newCoordinates = this.coordinates.change(0, 0, -5);
                logger.log(this.getAircraftInfo() + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                newCoordinates = this.coordinates.change(0, 0, -3);
                logger.log(this.getAircraftInfo() + ": It's foggy. Can't see anything.");
                break;
            case "SNOW":
                newCoordinates = this.coordinates.change(0, 0, -15);
                logger.log(this.getAircraftInfo() + ": It's snowing. We're gonna crash.");
                break;
        }

        super.updatePosition(newCoordinates);
    }
}
