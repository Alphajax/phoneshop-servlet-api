package com.es.phoneshop.web;

import com.es.phoneshop.model.product.dao.HashMapOrderDao;
import com.es.phoneshop.model.product.entities.Order;
import com.es.phoneshop.model.product.services.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderOverviewPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer url = req.getRequestURL();
        String orderId = url.substring(url.lastIndexOf("/")+1);
        System.out.println(orderId);

        OrderServiceImpl service = OrderServiceImpl.getInstance();
        Order order = service.getOrder(orderId);

        req.setAttribute("orderId",orderId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("/WEB-INF/pages/orderOverviewPage.jsp").forward(req,resp);
    }
}
