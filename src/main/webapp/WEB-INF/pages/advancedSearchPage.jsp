<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-14
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="advancedSearch" method="get">
        <label for="desc">Description:</label>
        <input type="text" name="description" id = "desc" ><br>

        <label for ="minPrice">Minimal price</label>
        <input type="text" name="minPrice" id="minPrice"><br>

        <label for ="maxPrice">Maximal price</label>
        <input type="text" name="maxPrice" id="maxPrice"><br>

        <label for="minStock">Minimal stock</label>
        <input type="text" name="minStock" id="minStock"><br>

        <label for="maxStock">Maximal stock</label>
        <input type="text" name="maxStock" id="maxStock"><br>

        <input type="submit" value="SEARCH">
    </form>
</body>
</html>
