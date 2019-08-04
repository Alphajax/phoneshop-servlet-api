package com.es.phoneshop.web;

import com.es.phoneshop.model.product.services.HttpSessionCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteItem/*")
public class DeleteCartItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer url = req.getRequestURL();
        long id = Long.parseLong(url.substring(url.lastIndexOf("/")+1,url.length()));
        HttpSessionCartService.getInstance().deleteItem(req, id);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
