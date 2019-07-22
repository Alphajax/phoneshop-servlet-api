package com.es.phoneshop.model.product.comparators;

import com.es.phoneshop.model.product.Product;

import java.util.Comparator;

public class ProductPriceComparatorASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return  (int) o2.getPrice().subtract(o1.getPrice()).longValue();
    }
}
