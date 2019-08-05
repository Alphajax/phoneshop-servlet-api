package com.es.phoneshop.web;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.entities.Cart;
import com.es.phoneshop.model.product.entities.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/update")
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> messages = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> newNumbers = new ArrayList<>();
        List<CartItem> cartItems;
        HttpSession session = req.getSession();
        cartItems = ((Cart) session.getAttribute("cart")).getCartItems();

        for (CartItem item: cartItems) {
            newNumbers.add(Integer.parseInt(req.getParameter(item.getProduct().getId().toString())));
            names.add(String.valueOf(item.getProduct().getId()));
        }

        for ( int i = 0; i < cartItems.size(); i++) {
            if( newNumbers.get(i) <= ArrayListProductDao.getInstance().getProduct(Long.parseLong(names.get(i))).getStock()) {
                cartItems.set(i, new CartItem(cartItems.get(i).getProduct(),newNumbers.get(i)));
                messages.add("Success");
            } else {
                messages.add("not enought");
            }
        }

        session.setAttribute("messages" , messages);
        resp.sendRedirect(req.getContextPath() + "/cart");

    }
}
