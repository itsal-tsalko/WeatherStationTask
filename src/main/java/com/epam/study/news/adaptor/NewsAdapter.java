package com.epam.study.news.adaptor;

import com.epam.study.news.NewsType;
import com.epam.study.news.TopicsAggregator;

/**
 * @author Iuliia Tsal-Tsalko
 */
public interface NewsAdapter<T> {

    NewsType getNewsType();

    String toUnifiedFormat(T feedData);

    default void publishNews(TopicsAggregator aggregator, T feedData){
        aggregator.publishNews(toUnifiedFormat(feedData), getNewsType());
    }
}
