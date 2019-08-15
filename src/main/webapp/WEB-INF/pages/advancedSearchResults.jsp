<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-15
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="results" type="java.util.ArrayList" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="products">
    <input type="submit" value="START Page">
</form>
<table>
    <c:forEach var="product" items="${results}">
        <tr>
            <td>
                <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
            </td>
            <td><a href="products/${product.id}">${product.description}</a></td>
            <td class="price">
                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
            </td>
            <td>${product.stock}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
