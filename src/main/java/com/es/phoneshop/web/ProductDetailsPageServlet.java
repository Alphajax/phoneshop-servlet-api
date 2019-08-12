package com.es.phoneshop.web;

import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.entities.Cart;
import com.es.phoneshop.model.product.entities.Product;
import com.es.phoneshop.model.product.services.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            product = ProductServiceImpl.getInstance().getProduct(id);
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
            ArrayListProductDao dao = ArrayListProductDao.getInstance();
            id = getId(req);
            int num = Integer.parseInt(req.getParameter("number"));
            HttpSession session = req.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            cart.addItem(dao.getProduct(id),num);

            req.setAttribute("product",product);
            req.setAttribute("products", dao.findProducts());
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
