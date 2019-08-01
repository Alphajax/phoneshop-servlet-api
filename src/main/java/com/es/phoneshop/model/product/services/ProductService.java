package com.es.phoneshop.model.product.services;

import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.entities.Product;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.comparators.*;

import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService implements ProductServiceI {

    private static ProductDao dao;

    private static ProductService instance;

    private ProductService(){
        dao = ArrayListProductDao.getInstance();
    }

    public static synchronized ProductService getInstance(){
        if (instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        return dao.getProduct(id);
    }

    @Override
    public List<Product> getProductByDescAndSort(String userRequest, String sortType) {
        List<Product> products=dao.findProducts();

        if(userRequest.length()!=0){
            products=searchProductsByDesc(userRequest,products);
        }
        sortProducts(sortType, products);

        return products;
    }

    private List<Product> searchProductsByDesc(String userRequest, List<Product> productList){
        Map<Product, Integer> matches = new HashMap<>();
        int maxMatches = 0;
        List<String> words = Arrays.asList(userRequest.split("\\s+")) ;
        List<Product> newProductList = new ArrayList<>();
        for (Product product: productList) {
            int matchesNum = filterProducts(words,product);
            if(maxMatches<matchesNum){
                maxMatches = matchesNum;
            }
            if (matchesNum  > 0) {
                matches.put(product,matchesNum);
            }
        }

        for( int i = maxMatches; i >= 1; i--){
            for (Map.Entry<Product,Integer> entry: matches.entrySet()) {
                if(entry.getValue().equals(i)){
                    newProductList.add(entry.getKey());
                }
            }
        }
        return newProductList;
    }

    private int filterProducts(List<String> words, Product product){
        String description = product.getDescription();
        int matches = 0;
        for (String wrd : words) {
            if(description.toLowerCase().contains(wrd.toLowerCase())){
                matches++;
            }
        }
        return matches;
    }

    private void sortProducts(String sortType, List<Product> productList){
        switch (sortType) {
            case "priceUp":
                productList.sort(new ProductPriceComparatorDESC());
                break;
            case "priceDown":
                productList.sort(new ProductPriceComparatorASC());
                break;
            case "descriptionUp":
                productList.sort(new ProductDescriptionComporatorASC());
                break;
            case "descriptionDown":
                productList.sort(new ProductDescriptionComparatorDESC());
                break;
        }
    }
}
