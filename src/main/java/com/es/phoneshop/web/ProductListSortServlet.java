package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.product.comparators.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/sort")
public class ProductListSortServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortType=req.getParameter("sorting");
        HttpSession session = req.getSession();
        List<Product> productList = (List<Product>)session.getAttribute("products");
        switch (sortType) {
            case "priceUp":
                productList.sort(new ProductPriceComparatorDESC());
                break;
            case "priceDown":
                productList.sort(new ProductPriceComparatorASC());
                break;
            case "descriptionUp":
                productList.sort(new ProductDescriptionComporatorASC());
                break;
            case "descriptionDown":
                productList.sort(new ProductDescriptionComparatorDESC());
                break;
        }
        session.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(req, resp);

    }
}
