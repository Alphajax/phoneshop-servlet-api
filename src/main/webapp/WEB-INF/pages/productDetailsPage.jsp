<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-07-21
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="details" type="com.es.phoneshop.model.product.Details" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Год выпуска ${details.year}</h3>
    <h3>Страна выпуска ${details.country}</h3>
    <h3>Процессор ${details.cpu}</h3>
    <h3>Оперативная память ${details.ram}</h3>
    <h3>Встроенная память ${details.flash}</h3>
</body>
</html>
