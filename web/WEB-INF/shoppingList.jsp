<%-- 
    Document   : shoppingList
    Created on : Oct 11, 2017, 2:06:04 PM
    Author     : 740991
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Hello ${username}</h1>
        <a href="register?name='logout'" type="hidden">Logout</a>
        <form action="add" method="Get">
            Item to add to cart<input type="text" name="addToCart">
            <input type="submit" name="action">
        </form>
        <form action="delete" method="Get">
            ${cart}
    </body>
</html>
