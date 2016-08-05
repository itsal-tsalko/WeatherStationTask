package com.epam.study.store;

import com.epam.study.store.customer.CustomerNotificationService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Store {

    final static Logger logger =  Logger.getLogger(Store.class);


    List<CustomerNotificationService> customerNotificationServices = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return itemList;
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void connect(CustomerNotificationService customer){
        customerNotificationServices.add(customer);
    }

    public void sendAdvertisementsForAllCustomers(){
        customerNotificationServices.stream().forEach(service->
                service.notifyCustomers(itemList.stream().filter(Item::isOnSale).collect(Collectors.toList())));
        logger.info("Advertisements has been sent to all customers");
    }

}
