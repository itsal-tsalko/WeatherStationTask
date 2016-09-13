package com.epam.study.store;

import com.epam.study.store.customer.CustomerNotificationService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Store {

    final static Logger logger = Logger.getLogger(Store.class);


    List<CustomerNotificationService> customerNotificationServices = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();

    Map<Subscriber, String> mapping = new HashMap<>();

    public void addCustomerNotificationServices(CustomerNotificationService customer) {
        customerNotificationServices.add(customer);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public void addItem(Item item) {
        itemList.add(item);
        for (Subscriber subscriber : mapping.keySet()) {
            if (mapping.get(subscriber).contains(item.type.toString())) {
                subscriber.notifySubscriber(item);
            }
        }
    }

    public void subscribe(Subscriber subscriber, String pattern) {
        mapping.put(subscriber, pattern);
    }

    public void unsubscribe(Subscriber subscriber) {
        mapping.remove(subscriber);
    }

    public void sendAdvertisementsForAllCustomers() {
        customerNotificationServices.stream().forEach(service ->
                service.notifyCustomers(itemList.stream().filter(Item::isOnSale).collect(Collectors.toList())));
        logger.info("Advertisements has been sent to all customers");
    }

}
