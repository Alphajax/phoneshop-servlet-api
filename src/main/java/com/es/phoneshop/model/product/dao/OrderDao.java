package com.es.phoneshop.model.product.dao;

import com.es.phoneshop.model.product.entities.Order;

public interface OrderDao {
    void addOrder(String safeId,Order order);
    Order getOrder(String safeID);
}
