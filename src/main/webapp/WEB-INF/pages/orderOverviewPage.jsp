<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-11
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="order" type="com.es.phoneshop.model.product.entities.Order" scope="request"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    Number of your order is ${requestScope.get("orderId")}
    <c:forEach var="cartItem" items="${order.cart.cartItems}">
        <tr align="center">
            <td>${cartItem.product.id}</td>
            <td><img src="${cartItem.product.imageUrl} " width="45%" height="45%"></td>
            <td>${cartItem.product.description}</td>
            <td>${cartItem.number}</td>
        </tr>
    </c:forEach>
</table>
    ${order.payment}<br>
    ${order.address}<br>
    ${order.date}<br>
    ${order.deliveryMode}<br>
    ${order.deliveryMode}<br>
    ${order.payment}<br>
</body>
</html>
