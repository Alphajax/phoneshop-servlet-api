package com.es.phoneshop.model.product.comparators;

import com.es.phoneshop.model.product.entities.Product;

import java.util.Comparator;

public class ProductSearchComparatorASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getMatchesNum()-o1.getMatchesNum();
    }
}
