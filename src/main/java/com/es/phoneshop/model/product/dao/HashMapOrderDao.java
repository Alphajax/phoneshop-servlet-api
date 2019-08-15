package com.es.phoneshop.model.product.dao;

import com.es.phoneshop.model.product.entities.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapOrderDao implements OrderDao {
    private static final Map<String,Order> orders = new ConcurrentHashMap<>();

    private static HashMapOrderDao instance;

    private HashMapOrderDao() {

    }

    public static synchronized HashMapOrderDao getInstance(){
        if (instance == null){
            instance = new HashMapOrderDao();
        }
        return instance;
    }

    @Override
    public void addOrder(String safeId, Order order) {
        Objects.requireNonNull(safeId);
        Objects.requireNonNull(order);

        orders.put(safeId,order);
    }

    @Override
    public Order getOrder(String safeID) {
        Objects.requireNonNull(safeID);

        return orders.get(safeID);
    }

    public int getSize() {
        return orders.size();
    }
}
