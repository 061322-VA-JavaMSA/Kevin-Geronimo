package com.revature.model;

import java.util.Objects;

public class Item {

    public enum Stock {
        AVAILABLE, OWNED
    }

    private int id;
    private String itemName;
    private Stock stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Stock getStatus() {
        return stock;
    }

    public void setStatus(Stock status) {
        this.stock = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Objects.equals(itemName, item.itemName) && stock == item.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, stock);
    }
}
