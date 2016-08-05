package com.epam.study.store;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Item {

    String type;
    String size;
    int count;
    boolean isWholeSaleItem;
    boolean onSale;

    public Item(String type, String size, int count, boolean isWholeSaleItem, boolean onSale) {
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
}
