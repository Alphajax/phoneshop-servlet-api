package com.es.phoneshop.model.product;

import com.es.phoneshop.exceptions.FindNotSuitProductException;
import com.es.phoneshop.exceptions.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest
{
    private ProductDao productDao;

    @Before
    public void setup() {
        productDao = new ArrayListProductDao();
    }

    @Test
    public void testFindProductsNoResults() {
        assertTrue(productDao.findProducts().isEmpty());
    }

    @Test
    public void testGetProduct() throws ProductNotFoundException, CloneNotSupportedException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "sgs", "Samsung Galaxy S", new BigDecimal(100), Currency.getInstance("USD"), 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));

    }

    @Test(expected = NullPointerException.class)
    public void testGetProductNullParameter() throws ProductNotFoundException, CloneNotSupportedException {
        productDao.getProduct(null);
    }

    @Test(expected = ProductNotFoundException.class)
    public void WrongIndexShouldThrowProductNotFoundException() throws ProductNotFoundException, CloneNotSupportedException {

        productDao.getProduct(14L);
    }

    @Test
    public void testFindPrducts() throws FindNotSuitProductException {
        List<Product> productList = productDao.findProducts();
        boolean isSuit = true;
        for (Product prd:productList) {
            if(prd.getStock()<=0 ||prd.getPrice()==null){
                isSuit=false;
                break;
            }
        }
        if(!isSuit) throw new FindNotSuitProductException();
    }

    @Test
    public void testSaveProduct() throws ProductNotFoundException, CloneNotSupportedException {
        Product product = new Product(1L, "sgs", "Samsung Galaxy S", new BigDecimal(100),
                Currency.getInstance("USD"), 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        Product testProduct;

        productDao.save(product);
        testProduct = productDao.getProduct(1L);

        assertEquals(product,testProduct);
    }

    @Test
    public void testSaveProductOverriding() throws ProductNotFoundException, CloneNotSupportedException {
        Product product = new Product(1L, "sgs6", "Samsung Galaxy S6", new BigDecimal(200),
                Currency.getInstance("USD"), 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        productDao.save(product);

        assertEquals(productDao.getProduct(1L),product);
    }

    @Test(expected = NullPointerException.class)
    public void SaveNullIdShouldThrowNullPointerException(){
        productDao.save(null);
    }



}
