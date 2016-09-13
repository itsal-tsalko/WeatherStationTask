package com.epam.study.store;

import com.epam.study.store.customer.Topics;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Item {

    Topics type;
    String size;
    int count;
    boolean isWholeSaleItem;
    boolean onSale;

    public Item(Topics type, String size, int count, boolean isWholeSaleItem, boolean onSale) {
        this.type = type;
        this.size = size;
        this.count = count;
        this.isWholeSaleItem = isWholeSaleItem;
        this.onSale = onSale;
    }

    public boolean isWholeSaleItem() {
        return isWholeSaleItem;
    }

    public boolean isOnSale() {
        return onSale;
    }

    @Override
    public String toString() {
        return "Item{" +
                "type=" + type +
                ", size='" + size + '\'' +
                ", count=" + count +
                '}';
    }
}
