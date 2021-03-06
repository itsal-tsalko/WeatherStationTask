package com.epam.study.store.customer;

import com.epam.study.store.Advertisement;
import com.epam.study.store.Item;
import com.epam.study.store.AdvertisementSender;
import com.epam.study.store.Store;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class RetailCustomerNotificationService implements CustomerNotificationService{

    final static Logger logger =  Logger.getLogger(RetailCustomerNotificationService.class);

    private AdvertisementSender sender;

    public RetailCustomerNotificationService(Store store, AdvertisementSender sender) {
        store.addCustomerNotificationServices(this);
        this.sender = sender;
    }

    public void notifyCustomers(List<Item> items) {
        List<Item> filteredItems  =  items.stream().filter(item -> !item.isWholeSaleItem()).collect(Collectors.toList());
        Advertisement advertisement = new Advertisement(filteredItems, LocalDateTime.now());
        sender.send(advertisement);
        logger.info("Advertisement has been sent to all retail customers");
    }
}
