package com.epam.study.weatherstation.display;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class ForecastDisplay extends WeatherStationDisplay {

    double humidity;
    double temperature;
    double pressure;

    public void update() {
        if (weatherStation.isPresent()) {
            double maxError = Math.random();
            double maxHumidityError = 5 * maxError;
            double maxTemperatureError = 3 * maxError;
            double maxPressureError = 1 * maxError;
            humidity = Math.floor(weatherStation.get().getWeather().getHumidity() * maxHumidityError);
            temperature = Math.floor(weatherStation.get().getWeather().getTemperature() * maxTemperatureError);
            pressure = Math.floor(weatherStation.get().getWeather().getPressure() * maxPressureError);
        }
        logger.info("Weather has been updated on "+ ForecastDisplay.class.getSimpleName());
    }

    public String display() {
        String PATTERN = "humidity = %.2f, temperature = %.2f, pressure = %.2f";
        return String.format(PATTERN, humidity, temperature, pressure);
    }
}
