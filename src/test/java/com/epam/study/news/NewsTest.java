package com.epam.study.news;

import com.epam.study.news.adaptor.StoreAdaptor;
import com.epam.study.news.adaptor.WeatherAdaptor;
import com.epam.study.store.Item;
import com.epam.study.store.Store;
import com.epam.study.store.customer.Topics;
import com.epam.study.weatherstation.Weather;
import com.epam.study.weatherstation.WeatherStation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class NewsTest {

    Store store = new Store();
    WeatherStation weatherStation = new WeatherStation();
    StoreAdaptor storeAdaptor = new StoreAdaptor();
    WeatherAdaptor weatherAdaptor = new WeatherAdaptor();
    TopicsAggregator topicsAggregator = new TopicsAggregator();

    @Test
    public void testUserReceiveNews() {
        store.addCustomerNotificationServices(storeAdaptor);
        weatherAdaptor.connect(weatherStation);
        User user1 = new User("Iuliia");
        User user2 = new User("Oleg");
        topicsAggregator.subscribe(user1, NewsType.WEATHER);
        topicsAggregator.subscribe(user2, NewsType.STORE, NewsType.WEATHER);
        weatherAdaptor.setTopicAggregator(topicsAggregator);
        storeAdaptor.setTopicsAggregator(topicsAggregator);
        Item item1 = new Item(Topics.SPORT, "M", 10, false, true);
        Item item2 = new Item(Topics.CLOTHES, "XL", 15, true, true);
        store.addItem(item1);
        store.addItem(item2);
        store.sendAdvertisementsForAllCustomers();
        Weather weather = new Weather(25, 66, 99);
        weatherStation.setWeather(weather);
        assertThat(user1.getReceivedNews().size(), is(1));
        assertTrue(user1.getReceivedNews().get(0).contains("Weather"));
        assertThat(user2.getReceivedNews().size(), is(2));
        assertTrue(user2.getReceivedNews().get(0).contains("Item"));
        assertTrue(user2.getReceivedNews().get(1).contains("Weather"));
    }
}
