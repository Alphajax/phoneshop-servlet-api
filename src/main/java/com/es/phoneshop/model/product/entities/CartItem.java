package com.es.phoneshop.model.product.entities;

import java.util.Objects;

public class CartItem {
    private long productId;
    private int number;

    public CartItem() {
    }

    public CartItem(long productId, int number) {
        this.productId = productId;
        this.number = number;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return productId == cartItem.productId &&
                number == cartItem.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, number);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + productId +
                ", number=" + number +
                '}';
    }
}
