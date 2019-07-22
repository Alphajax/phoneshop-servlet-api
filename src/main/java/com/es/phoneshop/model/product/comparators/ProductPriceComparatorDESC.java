package com.es.phoneshop.model.product.comparators;

import com.es.phoneshop.model.product.Product;

import java.util.Comparator;

public class ProductPriceComparatorDESC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return  (int) o1.getPrice().subtract(o2.getPrice()).longValue();
    }
}
