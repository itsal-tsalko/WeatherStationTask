package com.epam.study.news;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class User {

    final static Logger logger =  Logger.getLogger(TopicsAggregator.class);

    private String name;
    private List<String> receivedNews = new ArrayList<>();

    public List<String> getReceivedNews() {
        return receivedNews;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receiveNews(String news){
        receivedNews.add(news);
        logger.info(String.format("User %s has received news - %s", name, news));
    }
}
