<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-15
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="errors" type="java.util.List" scope="request"/>
<jsp:useBean id="requests" type="java.util.List" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <label for="desc">Description:</label>
    <input type="text" name="description" id = "desc" value="${requests.get(0)}" > ${errors.get(0)}<br>

    <label for ="minPrice">Minimal price</label>
    <input type="text" name="minPrice" id="minPrice" value="${requests.get(1)}">${errors.get(1)}<br>

    <label for ="maxPrice">Maximal price</label>
    <input type="text" name="minPrice" id="maxPrice" value="${requests.get(2)}">${errors.get(2)}<br>

    <label for="minStock">Minimal stock</label>
    <input type="text" name="minStock" id="minStock" value="${requests.get(3)}">${errors.get(3)}<br>

    <label for="maxStock">Maximal stock</label>
    <input type="text" name="maxStock" id="maxStock" value="${requests.get(4)}">${errors.get(4)}<br>

<form action="advanced">
    <input type="submit" value="BACK TO SEARCH "/>
</form>
</body>
</html>
