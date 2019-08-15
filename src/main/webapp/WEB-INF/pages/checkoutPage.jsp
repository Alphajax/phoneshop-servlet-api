<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alphajax_
  Date: 2019-08-10
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="cart" type="com.es.phoneshop.model.product.entities.Cart" scope="session"/>
<html>
<head>
    <title>Title</title>
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
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">In All</td>
            <td>${cart.sum}$</td>
            <td>${cart.num}</td>
        </tr>
    </table>
    <br><br>

    <form method="post" action="checkout">
        <label for="name">Name</label>
        <input type="text" id="name" name="name">
        <br><br>

        <label for="deliveryMode">Select Delivery Mode</label>
        <select name="deliveryMode" id="deliveryMode">
            <option value="courier">Courier</option>
            <option value="storePickup">Pick Up From The Store</option>
        </select>
        <br><br>

        <label for="date">Delivery Date</label>
        <input type="date" value="2019-04-01" id="date" readonly/>
        <br><br>

        <label for="cost">Delivery Cost</label>
        <input type="text" value="${cart.sum}$$"  id="cost" readonly/>
        <br><br>

        <label for="address">Adress</label>
        <input type="text" value="address" id="address"/>
        <br><br>

        <label for="payment">Payment</label>
        <select name="payment" id="payment">
            <option value="card">Credit card</option>
            <option value="money">Money</option>
        </select>
        <br><br>

        <input type="submit" value="Place order"/>

    </form>
</body>
</html>
