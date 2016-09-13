package com.epam.study.news;

import com.google.common.collect.Sets;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class TopicsAggregator {

    final static Logger logger =  Logger.getLogger(TopicsAggregator.class);

    public Map<NewsType, Set<User>> newsMapping = new HashMap<>();

    public void subscribe(User user, NewsType... newsTypes){
        for(NewsType news : newsTypes){
            subscribeUserToNewsType(user, news);
        }
    }

    private void subscribeUserToNewsType(User user, NewsType news){
        newsMapping.merge(news, Collections.singleton(user), Sets::union);
        logger.info(user.getName() + " has been subscribered to news on " + news);
    }

    public void unsubscribe(User user, NewsType... newsTypes){
          for(NewsType news : newsTypes){
              newsMapping.get(news).remove(user);
          }
    }

    public void publishNews(String news, NewsType newstype){
        newsMapping.get(newstype).forEach(user -> user.receiveNews(news));
    }
}
