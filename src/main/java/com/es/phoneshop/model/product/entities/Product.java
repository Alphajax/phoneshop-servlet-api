package com.es.phoneshop.model.product.entities;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Product {
    private Long id;
    private String code;
    private String description;
    /** null means there is no price because the product is outdated or new */
    private BigDecimal price;
    /** can be null if the price is null */
    private Currency currency;
    private int stock;
    private String imageUrl;

    public Product() {
    }


    public Product(Long id, String code, String description, BigDecimal price, Currency currency, int stock, String imageUrl) {
        Objects.requireNonNull(id);
        this.id = id;
        this.code = code;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public Product(Product p) {
        this.id = p.getId();
        this.code = p.getCode();
        this.description = p.getDescription();
        this.price = p.getPrice();
        this.currency = p.getCurrency();
        this.stock = p.getStock();
        this.imageUrl = p.getImageUrl();
    }


    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void subtractStock(int quantity) {
        this.stock = this.stock - quantity;
    }

    @Override
    public boolean equals(Object o) {
        Product product = (Product) o;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || id == null || product.getId() == null) return false;
        return id.equals(product.id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}'+'\n';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}