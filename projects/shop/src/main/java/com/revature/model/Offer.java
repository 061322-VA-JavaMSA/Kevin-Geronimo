package com.revature.model;

import java.util.Date;
import java.util.Objects;

public class Offer {
    public enum Status {
        PENDING, ACCEPTED
    }
    private float amount;
    private Date date;
    private User user;
    private Item item;

    private Status status;

    public float getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Float.compare(offer.amount, amount) == 0 && Objects.equals(date, offer.date) && Objects.equals(user, offer.user) && Objects.equals(item, offer.item) && status == offer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, user, item, status);
    }
}
