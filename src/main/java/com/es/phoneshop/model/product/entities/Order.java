package com.es.phoneshop.model.product.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Order {
    private final int id;
    private final Cart cart;
    private final String name;
    private final String deliveryMode;
    private final Date date;
    private final BigDecimal finalCost;
    private final String address;
    private final String payment;


    public Order(int id,Cart cart, String name, String deliveryMode, Date date, BigDecimal finalCost, String address, String payment) {
        this.id = id;
        this.cart = cart;
        this.name = name;
        this.deliveryMode = deliveryMode;
        this.date = date;
        this.finalCost = finalCost;
        this.address = address;
        this.payment = payment;

    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public String getName() {
        return name;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public String getAddress() {
        return address;
    }

    public String getPayment() {
        return payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, name, deliveryMode, date, finalCost, address, payment);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cart=" + cart +
                ", name='" + name + '\'' +
                ", deliveryMode='" + deliveryMode + '\'' +
                ", date=" + date +
                ", finalCost=" + finalCost +
                ", address='" + address + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
