package com.revature.model;

import java.util.Date;
import java.util.Objects;

public class Offer {
    private User user;
    private Item item;
    private float amount;
    private Date date;

    public Offer() {
        super();
    }

    public Offer(User user, Item item, float amount, Date date) {
        this.user = user;
        this.item = item;
        this.amount = amount;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Float.compare(offer.amount, amount) == 0 && Objects.equals(user, offer.user) && Objects.equals(item, offer.item) && Objects.equals(date, offer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, item, amount, date);
    }
}
