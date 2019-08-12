package com.es.phoneshop.model.product.entities;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cart {
    private volatile BigDecimal sum;
    private volatile int num;
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
        sum = BigDecimal.ZERO;
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    public synchronized List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public synchronized void addItem(Product product, int quantity){
        int index = -1;
        for (int i= 0; i < cartItems.size(); i++) {
            if(cartItems.get(i).getProduct().equals(product)) {
                index = i;
            }
        }
        if( index != -1) {
            cartItems.set(index, new CartItem(product, cartItems.get(index).getNumber() + quantity));
        } else {
            cartItems.add(new CartItem(product, quantity));
        }
        recalculate();
    }

    public synchronized void deleteItem(Product product) {
        cartItems.remove(new CartItem(product,0));
        recalculate();

    }

    public synchronized void updateItem(CartItem item) {
        int index = cartItems.indexOf(item);
        cartItems.set(index,item);
        recalculate();
    }

    private void recalculate(){
        sum = BigDecimal.ZERO;
        num = 0;
        for (CartItem item:cartItems) {
            sum = sum.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getNumber())));
            num+=item.getNumber();
        }
    }
}
