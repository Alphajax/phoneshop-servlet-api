package com.es.phoneshop.web;

import com.es.phoneshop.model.product.entities.Product;
import com.es.phoneshop.model.product.services.ProductServiceImpl;
import com.es.phoneshop.model.product.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet("/find")
public class ProductListFindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userRequest = req.getParameter("userRequest");
        String sortType = req.getParameter("sorting");

        ProductService service = ProductServiceImpl.getInstance();
        List<Product> products = service.getProductByDescAndSort(userRequest, sortType);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(req,resp);

    }
}
