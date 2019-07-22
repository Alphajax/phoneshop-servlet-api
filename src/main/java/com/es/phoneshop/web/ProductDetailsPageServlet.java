package com.es.phoneshop.web;

import com.es.phoneshop.exceptions.DetailsNotFoundException;
import com.es.phoneshop.model.product.Details;
import com.es.phoneshop.model.product.DetailsDao;
import com.es.phoneshop.model.product.HashMapDetailsDao;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/*")
public class ProductDetailsPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        StringBuffer url = req.getRequestURL();
        int ind = url.lastIndexOf("/");
        String productCode = url.substring(ind);
        if(productCode.length()>0){
            productCode = productCode.substring(1);
        }
        DetailsDao dao = HashMapDetailsDao.getInstance();
        req.setAttribute("details",dao.getDetails(productCode));
        req.getRequestDispatcher("/WEB-INF/pages/productDetailsPage.jsp").forward(req,resp);

    }
}
