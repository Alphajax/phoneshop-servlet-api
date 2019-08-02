<%@ page import="com.es.phoneshop.model.product.dao.ArrayListProductDao" %><%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-02
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cart" type="com.es.phoneshop.model.product.entities.Cart" scope="session"/>

<html>
<head>
    <title>Cart</title>
</head>
<body>
    <table border="1px solid black" >
        <thead align="center">
            <th>ID</th>
            <th>Img</th>
            <th>Desc</th>
            <th>Qty</th>
        </thead>

        <c:forEach var="cartItem" items="${cart.cartItems}">
            <tr align="center">
                <td>${cartItem.product.id}</td>
                <td><img src="${cartItem.product.imageUrl} " width="45%" height="45%"></td>
                <td>${cartItem.product.description}</td>
                <td>${cartItem.number}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
