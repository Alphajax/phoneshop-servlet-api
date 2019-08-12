package com.es.phoneshop.model.product.services;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.entities.Cart;
import com.es.phoneshop.model.product.entities.CartItem;
import com.es.phoneshop.model.product.entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class HttpSessionCartService {
    private static ProductDao dao = ArrayListProductDao.getInstance();
    private static HttpSessionCartService instance;

    private HttpSessionCartService(){

    }

    public static synchronized HttpSessionCartService getInstance(){
        if (instance == null){
            instance = new HttpSessionCartService();
        }
        return instance;
    }

    public void updateCart(HttpServletRequest req) {
        ArrayListProductDao dao = ArrayListProductDao.getInstance();
        long id;
        int newNum;
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        for (int i = 0; i< cart.getCartItems().size() ; i++){
            CartItem item = cart.getCartItems().get(i);
            id = item.getProduct().getId();
            newNum = Integer.parseInt(req.getParameter(String.valueOf(item.getProduct().getId())));
            if(newNum >= 0 && newNum<= dao.getProduct(id).getStock()) {
                HttpSessionCartService.getInstance().updateItem(cart,dao.getProduct(id) , newNum);
            }
        }
    }

    private synchronized void updateItem(Cart cart, Product product, int newNum) {
        cart.updateItem(new CartItem(product, newNum));
    }

    public synchronized void deleteItem(Cart cart, long id) {
        cart.deleteItem(dao.getProduct(id));
    }



}
