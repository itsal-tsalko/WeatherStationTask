package com.epam.study.store;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Advertisement {
    public List<Item> items;
    public LocalDateTime date;

    public Advertisement(List<Item> items, LocalDateTime date) {
        this.items = items;
        this.date = date;
    }
}
