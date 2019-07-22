package com.es.phoneshop.model.product;

import com.es.phoneshop.exceptions.DetailsNotFoundException;


import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;


public class HashMapDetailsDao implements DetailsDao {
    private static final Map<String, Details > detailsMap = new HashMap<>();
    private static HashMapDetailsDao instance;

    private HashMapDetailsDao(){

    }
    @Override
    public Details getDetails(String code) {
        code = requireNonNull(code, "Code must be not null");
        return detailsMap.get(code);
    }

    @Override
    public void save(String code, Details details) {
        code = requireNonNull(code,"Code must be not null");
        details = requireNonNull(details,"Details must be not null");
        detailsMap.put(code,details);
    }

    @Override
    public void delete(String code) throws DetailsNotFoundException {
        if(detailsMap.get(code)!=null){
            detailsMap.remove(code);
        } else {
            throw new DetailsNotFoundException();
        }

    }

    public static synchronized HashMapDetailsDao getInstance(){
        if(instance == null){
            instance = new HashMapDetailsDao();
        }
        return instance;
    }
}
