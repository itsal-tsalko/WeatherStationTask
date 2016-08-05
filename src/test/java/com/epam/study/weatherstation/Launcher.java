package com.epam.study.weatherstation;

import com.epam.study.weatherstation.display.CurrentConditionsDisplay;
import com.epam.study.weatherstation.display.ForecastDisplay;
import com.epam.study.weatherstation.display.StatisticsDisplay;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Launcher {

    WeatherStation weatherStation = new WeatherStation();
    DisplayManager displayManager = new DisplayManager();

    @Test
    public void mainStatisticDisplayTest(){
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        displayManager.connectDisplayToWeatherStation(statisticsDisplay, weatherStation);
        Weather weather = new Weather(25.7, 86, 99);
        weatherStation.setWeather(weather);
        weather = new Weather(26.7, 86, 99);
        weatherStation.setWeather(weather);
        String result = statisticsDisplay.display();
        Pattern pattern = Pattern.compile("temperature=(\\d+\\.?\\d*), humidity=(\\d+\\.?\\d*), pressure=(\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(result);
        matcher.find();
        assertThat(matcher.group(1), is("25.7"));
        assertThat(matcher.group(2), is("86.0"));
        assertThat(matcher.group(3), is("99.0"));
        matcher.find();
        assertThat(matcher.group(1), is("26.7"));
        assertThat(matcher.group(2), is("86.0"));
        assertThat(matcher.group(3), is("99.0"));
        displayManager.disconnectDisplayFromWeatherStation(statisticsDisplay, weatherStation);
    }

    @Test
    public void mainCurrentConditionsTest() {
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        displayManager.connectDisplayToWeatherStation(currentConditionsDisplay, weatherStation);
        Weather weather = new Weather(30.2, 50, 43);
        weatherStation.setWeather(weather);
        String result = currentConditionsDisplay.display();
        Pattern pattern = Pattern.compile("humidity = (\\d+\\.?\\d*), temperature = (\\d+.?\\d*), pressure = (\\d+.?\\d*)");
        Matcher matcher = pattern.matcher(result);
        matcher.find();
        assertThat(matcher.group(1), is("50.00"));
        assertThat(matcher.group(2), is("30.20"));
        assertThat(matcher.group(3), is("43.00"));
        displayManager.disconnectDisplayFromWeatherStation(currentConditionsDisplay, weatherStation);

    }

    @Test
    public void mainForecastConditionsTest() {
        ForecastDisplay forecastDisplay = new ForecastDisplay();
        displayManager.connectDisplayToWeatherStation(forecastDisplay, weatherStation);
        Weather weather = new Weather(22.2, 67, 49);
        weatherStation.setWeather(weather);
        String result = forecastDisplay.display();
        Pattern pattern = Pattern.compile("humidity = (\\d+\\.?\\d*), temperature = (\\d+.?\\d*), pressure = (\\d+.?\\d*)");
        Matcher matcher = pattern.matcher(result);
        matcher.find();
        assertNotEquals(matcher.group(1), "67.00");
        assertNotEquals(matcher.group(2), "22.2");
        assertNotEquals(matcher.group(3), "49.00");
        displayManager.disconnectDisplayFromWeatherStation(forecastDisplay, weatherStation);

    }
}
