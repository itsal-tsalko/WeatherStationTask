package weatheStation;

import org.apache.log4j.Logger;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class DisplayManager {
    final static Logger logger =  Logger.getLogger(DisplayManager.class);

    public void connectDisplayToWeatherStation(Display display, WeatherStation weatherStation) {
        display.connect(weatherStation);
        weatherStation.connect(display);
        logger.info(display.getClass().getSimpleName() + " has been connected to weather station");
    }

    public void disconnectDisplayFromWeatherStation(Display display, WeatherStation weatherStation) {
        display.disconnect();
        weatherStation.disconnect(display);
        logger.info(display.getClass().getSimpleName() + " has been disconnected from weather station");
    }
}
