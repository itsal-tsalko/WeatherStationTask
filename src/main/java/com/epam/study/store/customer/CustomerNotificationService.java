package com.epam.study.store.customer;

import com.epam.study.store.Item;

import java.util.List;

/**
 * @author Iuliia Tsal-Tsalko
 */
public interface CustomerNotificationService {
    void notifyCustomers(List<Item> items);
}
