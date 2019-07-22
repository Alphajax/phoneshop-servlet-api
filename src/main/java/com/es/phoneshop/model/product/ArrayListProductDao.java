package com.es.phoneshop.model.product;


import com.es.phoneshop.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.*;

public class ArrayListProductDao implements ProductDao {
    private static final List<Product> products = new ArrayList<>();

    private static ArrayListProductDao instance;

    private ArrayListProductDao() {

    }



    @Override
    public synchronized Product getProduct(Long id) throws ProductNotFoundException, CloneNotSupportedException {
        if(id == null){
            throw new NullPointerException();
        } else{
            Product tempProduct;
            for (Product product : products) {
                tempProduct = product;
                if (tempProduct.getId().equals(id)) {
                    return tempProduct.clone();
                }
            }
            throw new ProductNotFoundException();
        }

    }

    @Override
    public synchronized List<Product> findProducts(){
        return products.stream().filter(prod -> prod.getPrice() != null && prod.getStock() > 0).collect(Collectors.toList());
    }


    @Override
    public synchronized void save(Product product) {
        product = requireNonNull(product, "Product must be not null");
        int index=-1;
        Product tempProduct;
        for(int i = 0; i < products.size(); i++) {
            tempProduct = products.get(i);
            if (tempProduct.getId().equals(product.getId())){
                products.set(i,product);
                index=i;
            }
        }
        if(index==-1){
            products.add(product);
        }
    }

    @Override
    public synchronized void delete(Long id) {
        requireNonNull(id,"Id must be not null");
        Product tempProduct;
        for(int i =0;i < products.size();i++) {
            tempProduct = products.get(i);
            if (tempProduct.getId().equals(id)){
                products.remove(i);
                break;
            }
        }
    }

    public static synchronized ArrayListProductDao getInstance(){
        if (instance == null){
            instance = new ArrayListProductDao();
        }
        return instance;
    }

}
