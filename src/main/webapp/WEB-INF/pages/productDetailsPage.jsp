<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-07-26
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="product" type="com.es.phoneshop.model.product.entities.Product" scope="request"/>
<jsp:useBean id="message" type="java.lang.String" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${requestScope.get("num")} for
    ${requestScope.get("sum")}
    <img src="${product.imageUrl}" alt="image"/>
    <h3>${product.description}</h3>
    <h3>Price:${product.price}</h3>
    <h3>Stock:${product.stock}</h3>
    <p>${message}</p>
    <form method="post">
        Add <input type="text" name="number" value="1"/> products
        <input type="submit" value="ADD"/>
    </form>
</body>
</html>
