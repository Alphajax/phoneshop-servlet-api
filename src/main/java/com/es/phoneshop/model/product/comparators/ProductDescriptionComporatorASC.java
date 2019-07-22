package com.es.phoneshop.model.product.comparators;

import com.es.phoneshop.model.product.Product;

import java.util.Comparator;

public class ProductDescriptionComporatorASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
