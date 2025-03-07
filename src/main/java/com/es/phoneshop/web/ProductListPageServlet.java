package com.es.phoneshop.web;

import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductListPageServlet extends HttpServlet {

    private ProductDao dao;
    @Override
    public void init(){
        dao=ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", dao.findProducts());
        request.getRequestDispatcher("/").include(request,response);
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);

    }

}
