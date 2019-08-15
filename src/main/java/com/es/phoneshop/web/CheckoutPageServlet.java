package com.es.phoneshop.web;

import com.es.phoneshop.model.product.dao.HashMapOrderDao;
import com.es.phoneshop.model.product.entities.Cart;
import com.es.phoneshop.model.product.entities.Order;
import com.es.phoneshop.model.product.services.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@WebServlet("/checkout")
public class CheckoutPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/checkoutPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        OrderServiceImpl service = OrderServiceImpl.getInstance();

        String name = req.getParameter("name");
        String deliveryMode = req.getParameter("deliveryMode");
        Date date = new Date();
        BigDecimal cost = cart.getSum().add(BigDecimal.valueOf(10));
        String address = req.getParameter("address");
        String payment = req.getParameter("payment");

        Order order = new Order(HashMapOrderDao.getInstance().getSize(), cart, name, deliveryMode, date, cost, address , payment);
        //чистим карту
        String safeId = service.addOrder(order);

        System.out.println("/order/overview/"+safeId);
     //   req.getRequestDispatcher("/order/overview/"+safeId).forward(req,resp);
        resp.sendRedirect("order/overview/"+safeId);
    }
}
