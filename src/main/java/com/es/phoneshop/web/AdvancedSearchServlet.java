package com.es.phoneshop.web;

import com.es.phoneshop.model.product.entities.Product;
import com.es.phoneshop.model.product.services.AdvancedSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/advancedSearch")
public class AdvancedSearchServlet extends HttpServlet {
    private List<String> errors = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> requests = new ArrayList<>();

        errors.add("");

        String description = req.getParameter("description");
        requests.add(description);

        String minPrice = req.getParameter("minPrice");
        requests.add(minPrice);
        validate(minPrice);

        String maxPrice = req.getParameter("maxPrice");
        requests.add(maxPrice);
        validate(maxPrice);

        String minStock = req.getParameter("minStock");
        requests.add(minStock);
        validate(minStock);

        String maxStock = req.getParameter("maxStock");
        requests.add(maxStock);
        validate(maxStock);


        if(errors.contains("not a number")) {
            req.setAttribute("errors" , errors);
            req.setAttribute("requests",requests);
            req.getRequestDispatcher("/WEB-INF/pages/AdvancedSearchErrors.jsp").forward(req,resp);
            return;
        }
        List<Product> results = AdvancedSearchService.getInstance().getResults(requests);
        req.setAttribute("results",results);
        req.getRequestDispatcher("/WEB-INF/pages/advancedSearchResults.jsp").forward(req,resp);

    }

    private void validate(String number) {
        try {
            Integer.parseInt(number+"0");
            errors.add("");
        } catch (NumberFormatException e ) {
            errors.add("not a number");
        }
    }
}
