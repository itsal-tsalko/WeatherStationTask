package weatheStation;

/**
 * @author Iuliia Tsal-Tsalko
 */
public interface Display {

    String display();
    void update();
    void connect (WeatherStation weatherStation);
    void disconnect();
}
