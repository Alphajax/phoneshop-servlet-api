package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.product.comparators.ProductSearchComparatorASC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;


@WebServlet("/search")
public class ProductListSearchServlet extends HttpServlet {

    @Override
    public void init(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =req.getSession();
        List<Product> productList = (List<Product>) session.getAttribute("products");
        String userRequest=req.getParameter("userRequest");
        List<String> words = Arrays.asList(userRequest.split("\\s+")) ;
        productList =productList.stream().filter(product -> filterProducts(words,product)).collect(Collectors.toList());
        productList.sort(new ProductSearchComparatorASC());
        session.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(req, resp);
    }

    private boolean filterProducts(List<String> words, Product product){
        String description = product.getDescription();
        boolean isSuit = false;
        for (String wrd : words) {
            if(description.toLowerCase().contains(wrd.toLowerCase())){
                isSuit=true;
                product.setMatchesNum(product.getMatchesNum()+1);
            }
        }
        return isSuit;
    }



}
