package com.es.phoneshop.model.product.services;

import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.entities.Product;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.comparators.*;

import java.util.Arrays;
import java.util.List;
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
            List<String> words = Arrays.asList(userRequest.split("\\s+")) ;
            productList =productList.stream().filter(product -> filterProducts(words,product)).collect(Collectors.toList());
            productList.sort(new ProductSearchComparatorASC());
            return productList;
    }

    private boolean filterProducts(List<String> words, Product product){
        String description = product.getDescription();
        boolean isSuit = false;
        for (String wrd : words) {
            if(description.toLowerCase().contains(wrd.toLowerCase())){
                isSuit=true;
                product.setMatchesNum(product.getMatchesNum()+1);
            }
        }
        return isSuit;
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
