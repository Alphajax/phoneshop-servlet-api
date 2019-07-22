<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="products" type="java.util.ArrayList" scope="session"/>
<tags:master pageTitle="Product List">
  <p>
    Welcome to Expert-Soft training!
  </p>
  <form action="search">
    <input type="text" name="userRequest">
    <input type="submit" value="Search">
  </form>
  <form action="sort">
    <select name="sorting">
      <option value="nosort" >No Sort</option>
      <option value="priceUp">Price low to high</option>
      <option value="priceDown">Price high to low</option>
      <option value="descriptionUp">A-Z</option>
      <option value="descriptionDown">Z-A</option>
    </select>
    <input type="submit" value="SORT">
  </form>
  <form action="products">
    <input type="submit" value="SHOW ALL PRODUCTS">
  </form>
  <table>
    <thead>
      <tr>
        <td>Image</td>
        <td>Description</td>
        <td class="price">Price</td>
      </tr>
    </thead>
    <c:forEach var="product" items="${products}">
      <tr>
        <td>
          <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
        </td>
        <td><a href="products/${product.code}">${product.description}</a></td>
        <td class="price">
          <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
        </td>
      </tr>
    </c:forEach>
  </table>
</tags:master>