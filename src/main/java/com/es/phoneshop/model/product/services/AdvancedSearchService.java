package com.es.phoneshop.model.product.services;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.entities.Product;

import java.math.BigDecimal;
import java.util.*;

public class AdvancedSearchService {
    private static AdvancedSearchService instance;

    private AdvancedSearchService(){

    }

    public static synchronized AdvancedSearchService getInstance(){
        if (instance == null){
            instance = new AdvancedSearchService();
        }
        return instance;
    }

    public List<Product> getResults(List<String> requests) {

        if(requests.get(0) == null) {
            requests.set(0,"");
        }

        List<Product> results = ProductServiceImpl.getInstance().getProductByDescAndSort(requests.get(0),"nosort");
        List<Boolean> notEmptyRequests = checkRequests(requests);

        for(int i = 1 ; i < requests.size(); i ++) {
            if( requests.get(i) == null || requests.get(i) == "") {
                requests.set(i,"0");
            }
        }

        filterByPrice(results, Integer.parseInt(requests.get(1)), Integer.parseInt(requests.get(2)) , notEmptyRequests.get(1) , notEmptyRequests.get(2));
        filterByStock(results, Integer.parseInt(requests.get(3)), Integer.parseInt(requests.get(4)) , notEmptyRequests.get(3) , notEmptyRequests.get(4));

        return results;


    }

    private void filterByStock(List<Product> products, int min, int max, boolean hasMin, boolean hasMax) {
        for (int i = 0; i < products.size();) {
            if( hasMin & products.get(i).getStock()<min) {
                products.remove(i);
                continue;
            }
            if( hasMax & products.get(i).getStock()>max) {
                products.remove(i);
                continue;
            }
            i++;
        }
    }

    private void filterByPrice(List<Product> products,int min, int max, boolean hasMin, boolean hasMax) {
        for(int i = 0; i < products.size();) {
            if(hasMin & products.get(i).getPrice().compareTo(new BigDecimal(min)) < 0 ){
                    products.remove(i);
                    continue;
            }
            if(hasMax & products.get(i).getPrice().compareTo(new BigDecimal(max)) > 0 ){
                products.remove(i);
                continue;
            }
            i++;
        }
    }

    private List<Boolean> checkRequests(List<String> requests) {
        List<Boolean> notEmptyRequests = new ArrayList<>();
        for (String req: requests) {
            if(req!=null && req.length()>0) {
                notEmptyRequests.add(true);
            } else {
                notEmptyRequests.add(false);
            }
        }
        return notEmptyRequests;
    }



}
