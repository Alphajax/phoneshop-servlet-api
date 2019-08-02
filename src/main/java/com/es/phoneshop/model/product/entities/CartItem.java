package com.es.phoneshop.model.product.entities;

import java.util.Objects;

public class CartItem {
    private final Product product;
    private final int number;

    public CartItem(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return number == cartItem.number &&
                Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, number);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", number=" + number +
                '}';
    }
}
