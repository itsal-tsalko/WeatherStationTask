package com.epam.study.news.adaptor;

import com.epam.study.news.NewsType;
import com.epam.study.news.TopicsAggregator;
import com.epam.study.store.Item;
import com.epam.study.store.Store;
import com.epam.study.store.customer.CustomerNotificationService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class StoreAdaptor implements NewsAdapter<List<Item>>, CustomerNotificationService {

    final static Logger logger =  Logger.getLogger(StoreAdaptor.class);

    private TopicsAggregator topicsAggregator;

    public NewsType getNewsType() {
        return NewsType.STORE;
    }

    @Override
    public void notifyCustomers(List<Item> items) {
        publishNews(topicsAggregator, items);
        logger.info("Published news regarding items added to store");
    }

    @Override
    public String toUnifiedFormat(List<Item> items){
          return  items.toString();
    }

    public void setTopicsAggregator(TopicsAggregator topicsAggregator){
        this.topicsAggregator = topicsAggregator;
    }
}
