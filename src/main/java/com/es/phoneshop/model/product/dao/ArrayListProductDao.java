package com.es.phoneshop.model.product.dao;


import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.*;

public class ArrayListProductDao implements ProductDao {
    private static final List<Product> products = new ArrayList<>();

    private static ArrayListProductDao instance;

    private ArrayListProductDao() {

    }

    public static synchronized ArrayListProductDao getInstance(){
        if (instance == null){
            instance = new ArrayListProductDao();
        }
        return instance;
    }

    @Override
    public synchronized Product getProduct(Long id) throws ProductNotFoundException{
        if(id == null){
            throw new NullPointerException();
        } else{
            for (Product prd: products) {
                if(prd.getId().equals(id)){
                    return new Product(prd);
                }
            }
            throw new ProductNotFoundException("Product with id = " + id +" not found");
        }

    }

    @Override
    public synchronized List<Product> findProducts(){
        return products.stream().filter(prod -> prod.getPrice() != null && prod.getStock() > 0).collect(Collectors.toList());
    }


    @Override
    public synchronized void save(Product product) {
        requireNonNull(product, "Product must be not null");
        int index = products.indexOf(product);
        if(index==-1){
            products.add(product);
        } else {
            products.set(index,product);
        }
    }

    @Override
    public synchronized void delete(Long id) {
        requireNonNull(id,"Id must be not null");
        for (int i = 0; i < products.size(); i++) {
            if( products.get(i).getId().equals(id)) {
                products.remove(i);
                break;
            }
        }
    }



}
