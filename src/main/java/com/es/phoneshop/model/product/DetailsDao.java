package com.es.phoneshop.model.product;

import com.es.phoneshop.exceptions.DetailsNotFoundException;

import java.util.List;

public interface DetailsDao {
    Details getDetails(String code);
    void save(String code, Details details);
    void delete(String code) throws DetailsNotFoundException;
}
