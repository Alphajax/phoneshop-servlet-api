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
        CartItem item = (CartItem) o;
        return product.equals(item.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", number=" + number +
                '}';
    }
}
