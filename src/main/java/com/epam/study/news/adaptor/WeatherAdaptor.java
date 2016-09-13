package com.epam.study.news.adaptor;

import com.epam.study.news.NewsType;
import com.epam.study.news.TopicsAggregator;
import com.epam.study.weatherstation.Weather;
import com.epam.study.weatherstation.WeatherStation;
import com.epam.study.weatherstation.display.Display;
import org.apache.log4j.Logger;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class WeatherAdaptor implements NewsAdapter<Weather>, Display {

    final static Logger logger =  Logger.getLogger(WeatherAdaptor.class);

    private WeatherStation weatherStation;
    private TopicsAggregator topicsAggregator;

    public NewsType getNewsType() {
        return NewsType.WEATHER;
    }

    @Override
    public void update() {
        publishNews(topicsAggregator, weatherStation.getWeather());
        logger.info("Published news regarding weather update");
    }

    @Override
    public String toUnifiedFormat(Weather weather){
        return  weather.toString();
    }

    public String display(){
        return null;
    }

    @Override
    public void connect(WeatherStation weatherStation) {
         this.weatherStation = weatherStation;
         weatherStation.connect(this);
    }

    public void disconnect(){
    }

    public void setTopicAggregator(TopicsAggregator topicsAggregator){
        this.topicsAggregator = topicsAggregator;
    }

}
