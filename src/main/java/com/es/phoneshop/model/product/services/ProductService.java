package com.es.phoneshop.model.product.services;

import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByDescAndSort(String userRequest, String sortType);
    Product getProduct(long id) throws ProductNotFoundException;
}
