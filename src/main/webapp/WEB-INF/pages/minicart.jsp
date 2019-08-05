<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-05
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="cart" type="com.es.phoneshop.model.product.entities.Cart" scope="session"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
    ${cart.num}
    ${cart.sum}
</body>
</html>
