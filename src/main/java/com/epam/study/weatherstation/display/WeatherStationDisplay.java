package com.epam.study.weatherstation.display;

import org.apache.log4j.Logger;
import com.epam.study.weatherstation.WeatherStation;

import java.util.Optional;

/**
 * @author Iuliia Tsal-Tsalko
 */
public abstract class WeatherStationDisplay implements Display {

    final static public Logger logger =  Logger.getLogger(WeatherStationDisplay.class);

    protected Optional<WeatherStation> weatherStation = Optional.empty();

    public abstract void update();

    public abstract String display();

    public void connect (WeatherStation weatherStation){
        this.weatherStation = Optional.of(weatherStation);
    }

    public void disconnect (){
        weatherStation = Optional.empty();
    }
}
