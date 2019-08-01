package com.es.phoneshop.web;

import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.entities.Product;
import com.es.phoneshop.model.product.services.HttpSessionCartService;
import com.es.phoneshop.model.product.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/products/*")
public class ProductDetailsPageServlet extends HttpServlet {
    private Product product;
    private long id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = getId(req);
        try {
            req.setAttribute("message","Chose how much do you want to add");
            product = ProductService.getInstance().getProduct(id);
            req.setAttribute("product",product);
            req.getRequestDispatcher("/WEB-INF/pages/productDetailsPage.jsp").forward(req,resp);
        } catch (ProductNotFoundException e) {
            req.setAttribute("message", "product wirh id "+ id+ " not found");
            req.getRequestDispatcher("/WEB-INF/pages/productNotFound.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int number = Integer.parseInt(req.getParameter("number"));
            int stock = ArrayListProductDao.getInstance().getProduct(id).getStock();
            if(stock>=number){
                HttpSessionCartService.getInstance().add(req.getSession(),id,number);
                req.setAttribute("message","Added Successfully");
                Product product = ArrayListProductDao.getInstance().getProduct(id);
                product.setStock(product.getStock()-number);
                ArrayListProductDao.getInstance().save(product);
            } else{
                req.setAttribute("message" , "not enought products");
            }

            req.setAttribute("product",product);
            resp.sendRedirect(req.getContextPath() + req.getServletPath() + req.getPathInfo());
            //req.getRequestDispatcher("/WEB-INF/pages/productDetailsPage.jsp").forward(req,resp);
        } catch (ProductNotFoundException e) {
            req.getRequestDispatcher("/WEB-INF/pages/productNotFound.jsp").forward(req,resp);
        } catch (NumberFormatException e ){
            req.setAttribute("product",product);
            req.setAttribute("message", "please enter numer");
            req.getRequestDispatcher("/WEB-INF/pages/productDetailsPage.jsp").forward(req,resp);
        }
    }

    private long getId(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String id = url.substring(url.lastIndexOf("/")+1);
        return Long.parseLong(id);
    }
}
