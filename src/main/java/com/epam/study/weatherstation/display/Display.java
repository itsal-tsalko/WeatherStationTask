package com.epam.study.weatherstation.display;

import com.epam.study.weatherstation.WeatherStation;

/**
 * @author Iuliia Tsal-Tsalko
 */
public interface Display {

    String display();
    void update();
    void connect (WeatherStation weatherStation);
    void disconnect();
}
