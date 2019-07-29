package com.es.phoneshop.model.product.services;

import com.es.phoneshop.model.product.entities.Cart;
import com.es.phoneshop.model.product.entities.CartItem;

import javax.servlet.http.HttpSession;
import java.util.List;

public class HttpSessionCartService {
    private static HttpSessionCartService instance;

    private HttpSessionCartService(){

    }

    public static synchronized HttpSessionCartService getInstance(){
        if (instance == null){
            instance = new HttpSessionCartService();
        }
        return instance;
    }

    public void add(HttpSession session,long productID, int quantity){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.add(new CartItem(productID,quantity));
        cart.setCartItems(cartItems);
        session.setAttribute("cart",cart);
    }
}
