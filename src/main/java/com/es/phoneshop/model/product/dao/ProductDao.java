package com.es.phoneshop.model.product.dao;


import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.entities.Product;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id) throws ProductNotFoundException;
    List<Product> findProducts();
    void save(Product product);
    void delete(Long id);
}
