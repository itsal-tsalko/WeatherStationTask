package weatheStation;

import org.apache.log4j.Logger;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class CurrentConditionsDisplay extends WeatherStationDisplay {

    double humidity;
    double temperature;
    double pressure;

    public void update() {
        if (weatherStation.isPresent()) {
            humidity = weatherStation.get().getWeather().getHumidity();
            temperature = weatherStation.get().getWeather().getTemperature();
            pressure = weatherStation.get().getWeather().getPressure();
        }
        logger.info("Weather has been updated on current condition display");
    }

    public String display() {
        String PATTERN = "humidity = %.2f, temperature = %.2f, pressure = %.2f";
        return String.format(PATTERN, humidity, temperature, pressure);
    }
}
