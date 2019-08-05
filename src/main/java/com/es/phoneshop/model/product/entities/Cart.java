package com.es.phoneshop.model.product.entities;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private BigDecimal sum;
    private int num;
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public synchronized void addItem(long id, int quantity){
        int index = -1;
        for (int i = 0; i < cartItems.size(); i++){
            if(cartItems.get(i).getProduct().getId().equals(id)) {
                index = i;
            }
        }

        if ( index != -1){
            CartItem item = cartItems.get(index);
            cartItems.set(index,new CartItem(item.getProduct(), item.getNumber()+quantity));
        } else {
            cartItems.add(new CartItem(ArrayListProductDao.getInstance().getProduct(id),quantity));
        }
        findSumm();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartItems, cart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItems);
    }

    public void deleteItem(long productID) {
        int index = -1;
        for (int i = 0; i < cartItems.size(); i++){
            if(cartItems.get(i).getProduct().getId().equals(productID)) {
                index = i;
            }
        }

        if(index != -1) {
            cartItems.remove(index);
        }
        findSumm();

    }

    void findSumm(){
        sum = BigDecimal.ZERO;
        num = 0;
        for (CartItem item:cartItems) {
            sum = sum.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getNumber())));
            num+=item.getNumber();
        }
    }
}
