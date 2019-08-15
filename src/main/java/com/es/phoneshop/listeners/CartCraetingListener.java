package com.es.phoneshop.listeners;

import com.es.phoneshop.model.product.entities.Cart;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

public class CartCraetingListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("work");
        Cart cart = new Cart();
        httpSessionEvent.getSession().setAttribute("cart",cart);
        httpSessionEvent.getSession().setAttribute("messages", new ArrayList<String>());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
