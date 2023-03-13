<%-- 
    Document   : createNewAccount
    Created on : Mar 13, 2023, 3:07:41 PM
    Author     : admin
--%>

<%@page import="tinnq.registration.RegistrationInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Result</title>
    </head>
    <body>
        <% RegistrationInsertError error = (RegistrationInsertError) request.getAttribute("INSERTERR");%>
        <h1>Create New Account</h1>
        <form action="InsertController">
            <label for="username">Username</label>
            <input type="text" name="txtUsername" value="<%= request.getParameter("txtUsername")%>" id="username"/> (6-20 chars)<br> <br>
            <% if (error != null) {
                    if (error.getUsernameLengthErr() != null) {
            %>
            <font color="red"> <%= error.getUsernameLengthErr()%> </font> <br> <br>
            <%
                    }
                }
            %>
            <label for="password">Password</label>
            <input type="password" name="txtPassword" value="<%= request.getParameter("txtPassword")%>" id="password"/> (6-30 chars)<br> <br>
            <% if (error != null) {
                    if (error.getPasswordLengthErr() != null) {
            %>
            <font color="red"> <%= error.getPasswordLengthErr() %> </font> <br> <br>
            <%
                    }
                }
            %>
            <label for="confirm">Confirm</label>
            <input type="password" name="txtConfirm" value="<%= request.getParameter("txtConfirm")%>" id="confirm"/> <br> <br>
            <% if (error != null) {
                    if (error.getConfirmNotMatch() != null) {
            %>
            <font color="red"> <%= error.getConfirmNotMatch() %> </font> <br> <br>
            <%
                    }
                }
            %>
            <label for="fullname">Full name</label>
            <input type="text" name="txtFullname" value="<%= request.getParameter("txtFullname")%>" id="fullname"/> (2-50 chars)<br> <br>
            <% if (error != null) {
                    if (error.getFullNameLengthErr() != null) {
            %>
            <font color="red"> <%= error.getFullNameLengthErr() %> </font> <br> <br>
            <%
                    }
                }
            %>
            <input type="submit" value="Create New Account" name="btAction" />
            <button type="submit" formaction="createNewAccount.html">Reset</button> <br> <br>
            <% if (error != null) {
                    if (error.getUsernameIsExisted() != null) {
            %>
            <font color="red"> <%= error.getUsernameIsExisted() %> </font> <br> <br>
            <%
                    }
                }
            %>
        </form>
    </body>
</html>
