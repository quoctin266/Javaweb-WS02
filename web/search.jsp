<%-- 
    Document   : search
    Created on : Feb 20, 2023, 12:56:54 PM
    Author     : admin
--%>
<%@page import="java.util.List" %>
<%@page import="tinnq.registration.RegistrationDTO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          String username="";
          Cookie[] cookies = request.getCookies();
          if (cookies != null) {
            for (int i=0; i<cookies.length; i++) {
                if(cookies[i].getName().equals("Username")) {
                    username = cookies[i].getValue();
                }
            }
          }
        %>
        <h1 style="color:red">Welcome <%=username%></h1>
        <h1>Welcome to DB Servlet</h1>
        <form action="MainController">
            <label for="name">Name</label>
            <input type="text" name="txtSearchValue" value="" id="name"/>
            <br> <br>
            <input type="submit" value="Search" name="btAction" />
            <button type="submit" formaction="index.html">Return</button>
        </form>
        <br>
        <br>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            String firstLoad = (String)request.getAttribute("firstLoad");
            List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCHRESULT");
            if (searchValue != null) out.println("Your search value is " + searchValue);
        %>
       
        <br>
        <%
            if (result != null) {
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th> 
                    <th>Username</th> 
                    <th>Password</th> 
                    <th>Last Name</th> 
                    <th>Role</th> 
                    <th>Delete</th> 
                    <th>Update</th> 
                </tr>
            </thead>
            <tbody>
                <%!
                    int count = 0;
                    String checked;
                %>
                <%
                    for (RegistrationDTO x : result) {
                        checked=null;
                        if(x.isRole()) checked = "checked";
                %>
                <tr>
                    <form action="UpdateController">
                        <td>
                            <%=++count%>
                        </td>
                        <td>
                            <%=x.getUsername()%>
                            <input type='hidden' name='txtUsername' value= '<%=x.getUsername()%>'/>
                        </td>
                        <td>
                            <input type='password' name='txtPassword' value='<%=x.getPassword()%>'/>
                        </td>
                        <td>
                            <%=x.getLastname()%> 
                        </td>
                        <td>
                            <input type='checkbox' name='chkRole' value='ADMIN'  <%=checked%>  />
                        </td>
                        <td>
                            <a href=DeleteController?txtSearchValue=<%=searchValue%>&username=<%=x.getUsername()%> >Delete</a>
                        </td>
                        <td>
                            <input type='hidden' name='txtSearchValue' value='<%=searchValue%>'/>
                            <input type='submit' name='btAction' value='Update'/>
                        </td>
                    </form>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <br>
        
        <%
            if (request.getAttribute("UpdateStatus") != null) {
                    String updateStatus = (String)request.getAttribute("UpdateStatus");
                    out.println(updateStatus);
                }
            if (request.getAttribute("DeleteResult") != null) {
                    int row = (int)request.getAttribute("DeleteResult");
                    out.println(row + " row(s) were affected.");
                }
            }
            else if (firstLoad != null){
                out.println("<h2>No record is matched</h2>");
            }
        %>
        
    </body>
</html>
