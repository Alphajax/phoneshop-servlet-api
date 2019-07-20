package com.es.phoneshop.model.product;


import com.es.phoneshop.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id) throws CloneNotSupportedException, ProductNotFoundException;
    List<Product> findProducts();
    void save(Product product);
    void delete(Long id);
}
