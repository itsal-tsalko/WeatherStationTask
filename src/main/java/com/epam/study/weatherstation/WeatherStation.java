package com.epam.study.weatherstation;

import com.epam.study.weatherstation.display.Display;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class WeatherStation {

    final static Logger logger =  Logger.getLogger(WeatherStation.class);

    private Weather weather;
    private List<Display> listeners = new ArrayList<Display>();


    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
        notifyAllListeners();
        logger.info("Weather has been updated");
    }

    public void connect(Display listener) {
        listeners.add(listener);
    }

    public void disconnect(Display listener) {
        listeners.remove(listener);
    }

    public void notifyAllListeners() {
        for (Display listener : listeners) {
            listener.update();
        }
    }
}
