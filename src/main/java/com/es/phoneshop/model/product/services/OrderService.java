package com.es.phoneshop.model.product.services;

import com.es.phoneshop.model.product.entities.Order;

public interface OrderService {
    String addOrder(Order order);
    Order getOrder(String safeId);
}
