package weatheStation;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class StatisticsDisplay extends WeatherStationDisplay {

    List<WeatherSnapshot> weatherSnapshots = new ArrayList<WeatherSnapshot>();

    public void update() {
        if (weatherStation.isPresent()) {
            WeatherSnapshot weatherSnapshot = new WeatherSnapshot(weatherStation.get().getWeather(), LocalDateTime.now());
            weatherSnapshots.add(weatherSnapshot);
        }
        logger.info("Weather has been updated on statistic display");
    }

    public String display() {
        StringBuilder stringBuilder = new StringBuilder();
        for (WeatherSnapshot item : weatherSnapshots) {
            stringBuilder.append(item).append("\n");
        }
        return stringBuilder.toString();
    }

    private static class WeatherSnapshot {
        private Weather weather;
        private LocalDateTime date;

        public WeatherSnapshot(Weather weather, LocalDateTime date) {
            this.weather = weather;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Weather on [" +
                    "date=" + date +
                    "]" + " : " + weather;
        }
    }
}
