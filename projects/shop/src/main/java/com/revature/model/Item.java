package com.revature.model;

import java.util.Objects;

public class Item {

    enum Status {
        AVAILABLE, OWNED
    }

    private int id;
    private String itemName;
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Objects.equals(itemName, item.itemName) && status == item.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, status);
    }
}
