package com.es.phoneshop.listeners;

import com.es.phoneshop.model.product.entities.Cart;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CartCraetingListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Cart cart = new Cart();
        httpSessionEvent.getSession().setAttribute("cart",cart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
