package com.es.phoneshop.model.product.services;

import com.es.phoneshop.model.product.dao.HashMapOrderDao;
import com.es.phoneshop.model.product.entities.Order;

import java.util.Objects;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;

    private OrderServiceImpl(){

    }

    public static synchronized OrderServiceImpl getInstance(){
        if (instance == null){
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public String addOrder(Order order) {
        Objects.requireNonNull(order);
        HashMapOrderDao dao = HashMapOrderDao.getInstance();
        String safeId = String.valueOf(Math.abs(order.hashCode()));
        dao.addOrder(safeId,order);
        return safeId;
    }

    @Override
    public Order getOrder(String safeId) {
        HashMapOrderDao dao = HashMapOrderDao.getInstance();
        return dao.getOrder(safeId);
    }
}
