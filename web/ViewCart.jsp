<%-- 
    Document   : ViewCart
    Created on : Mar 3, 2023, 2:19:28 PM
    Author     : ADMINS
--%>

<%@page import="java.util.Map"%>
<%@page import="tinnq.shoppingCart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center">Your Cart</h1>
        <%
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getItems() != null) {

        %>
        <table border="1" style="width: 30%;margin-left:auto;margin-right:auto">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Select amount</th>
                </tr>
            </thead>
            <tbody>
            <form action="MainController">
                <%                        
                    Map<String, Integer> items = cart.getItems();
                    int count = 0;
                    for (Map.Entry item : items.entrySet()) {
                %>
                <tr>
                    <td style="text-align: center"> <%=++count%> </td>
                    <td style="text-align: center"> <%=item.getKey()%> </td>
                    <td style="text-align: center"> <%=item.getValue()%> </td>
                    <td style="width: 10%;text-align: center"> 
                        <input type="number" name="amountOfItem" min="0" max="<%=item.getValue()%>" value="0"/>
                        <input type="hidden" name="itemName" value="<%=item.getKey()%>" />
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="3" style="text-align: center">
                        <button type="submit" formaction="bookStore.html" style="padding: 0 175px">Add more items</button>
                    </td>
                    <td>
                        <input type="submit" value="Remove items" name="btAction" />
                    </td>
                </tr>
            </form>
        </tbody>
    </table>
    <%
                    return;
                }
            }
        }
    %>
    <h2>Cart empty</h2>
</body>
</html>
