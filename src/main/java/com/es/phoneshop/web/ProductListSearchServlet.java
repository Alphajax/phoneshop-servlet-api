package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;


@WebServlet("/search")
public class ProductListSearchServlet extends HttpServlet {

    private ProductDao dao;

    @Override
    public void init(){
        dao = new ArrayListProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Product,Integer> matchesNum = new HashMap<>();
        String userRequest=req.getParameter("userRequest");
        List<String> words = Arrays.asList(userRequest.split("\\s+")) ;
        System.out.println(words);
        List<Product> productList = dao.findProducts();
        productList =productList.stream().filter(product -> filterProducts(words,product)).collect(Collectors.toList());
        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(req, resp);

    }

    private boolean filterProducts(List<String> words, Product product){
        String description = product.getDescription();
        boolean isSuit = false;
        for (String wrd : words) {
            if(description.contains(wrd)){
                isSuit=true;
            }
        }
        return isSuit;
    }


}
