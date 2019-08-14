package com.es.phoneshop.model.product.comparators;

import com.es.phoneshop.model.product.entities.Product;

import java.util.Comparator;

public class ProductPriceComparatorASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return  o1.getPrice().compareTo(o2.getPrice());
    }
}
