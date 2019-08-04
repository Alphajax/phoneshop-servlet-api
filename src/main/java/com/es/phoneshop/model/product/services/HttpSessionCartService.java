package com.es.phoneshop.model.product.services;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.entities.Cart;
import com.es.phoneshop.model.product.entities.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

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

    public void add(HttpServletRequest req,long productID, int quantity){

        int number = Integer.parseInt(req.getParameter("number"));
        int stock = ArrayListProductDao.getInstance().getProduct(productID).getStock();
        if(stock>=number){
            HttpSessionCartService.getInstance().synchronizedAdding(req.getSession(),productID,quantity);
            req.setAttribute("message","Added Successfully");
            Product product = ArrayListProductDao.getInstance().getProduct(productID);
            product.setStock(product.getStock()-number);
            ArrayListProductDao.getInstance().save(product);
        } else{
            req.setAttribute("message" , "not enought products");
        }

    }

    public void deleteItem(HttpServletRequest req, long id) {
        HttpSession session = req.getSession();
        synchronizedDeleting(session, id);
    }

    private void synchronizedAdding(HttpSession session, long productID, int quantity){
        Cart cart;
        synchronized (session){
            cart = (Cart) session.getAttribute("cart");
        }
        synchronized (cart){
            cart.addItem(productID,quantity);
        }
    }

    private void synchronizedDeleting(HttpSession session , long productID) {
        Cart cart;
        synchronized (session) {
            cart = (Cart) session.getAttribute("cart");
        }
        synchronized (cart) {
            cart.deleteItem(productID);
        }
    }

}
