package com.es.phoneshop.model.product;

import com.es.phoneshop.exceptions.ProductNotFoundException;
import com.es.phoneshop.model.product.dao.ArrayListProductDao;
import com.es.phoneshop.model.product.dao.ProductDao;
import com.es.phoneshop.model.product.entities.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayListProductDaoTest
{
    private ProductDao productDao;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
    }

    @Test
    public void testGetProduct() throws ProductNotFoundException, CloneNotSupportedException {
        productDao.save(new Product(1L, "sgs", "Samsung Galaxy S", new BigDecimal(100), Currency.getInstance("USD"), 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        Product product = productDao.getProduct(1L);
        assertEquals(1L, (long) product.getId());
        assertEquals("sgs", product.getCode());
        assertEquals("Samsung Galaxy S", product.getDescription());
        assertEquals(product.getPrice(), new BigDecimal(100));
        assertEquals(product.getCurrency(), Currency.getInstance("USD"));
        assertEquals(100, product.getStock());
        assertEquals("https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg", product.getImageUrl());

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
    public void testFindPrducts() {
        List<Product> productList = productDao.findProducts();
        boolean isSuit = true;
        for (Product prd:productList) {
            if(prd.getStock()<=0 ||prd.getPrice()==null){
                isSuit=false;
                break;
            }
        }
        assertTrue(isSuit);
    }

    @Test
    public void testSaveProduct() throws ProductNotFoundException, CloneNotSupportedException {
        Product product = new Product(1L, "sgs", "Samsung Galaxy S", new BigDecimal(100),
                Currency.getInstance("USD"), 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        Product testProduct;

        productDao.save(product);
        testProduct = productDao.getProduct(1L);

        assertEquals(1L, (long) testProduct.getId());
        assertEquals("sgs", testProduct.getCode());
        assertEquals("Samsung Galaxy S", testProduct.getDescription());
        assertEquals(testProduct.getPrice(), new BigDecimal(100));
        assertEquals(testProduct.getCurrency(), Currency.getInstance("USD"));
        assertEquals(100, testProduct.getStock());
        assertEquals("https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg", testProduct.getImageUrl());

    }

    @Test
    public void testSaveProductOverriding() throws ProductNotFoundException, CloneNotSupportedException {
        Product product = new Product(1L, "sgs6", "Samsung Galaxy S6", new BigDecimal(200),
                Currency.getInstance("USD"), 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        productDao.save(product);

        assertEquals(1L, (long) product.getId());
        assertEquals("sgs6", product.getCode());
        assertEquals("Samsung Galaxy S6", product.getDescription());
        assertEquals(product.getPrice(), new BigDecimal(200));
        assertEquals(product.getCurrency(), Currency.getInstance("USD"));
        assertEquals(100, product.getStock());
        assertEquals("https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg", product.getImageUrl());

    }

    @Test(expected = NullPointerException.class)
    public void SaveNullIdShouldThrowNullPointerException(){
        productDao.save(null);
    }



}
