package com.revature.model;

import java.time.LocalDate;
import java.util.Objects;

public class Offer {
    private User user;
    private Item item;
    private double amount;
    private LocalDate date;

    public Offer() {
        super();
    }

    public Offer(User user, Item item, LocalDate date, double amount) {
        this.user = user;
        this.item = item;
        this.date = date;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.amount, amount) == 0 && Objects.equals(user, offer.user) && Objects.equals(item, offer.item) && Objects.equals(date, offer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, item, amount, date);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "user=" + user +
                ", item=" + item +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
