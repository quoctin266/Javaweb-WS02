/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package tinnq.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import tinnq.registration.RegistrationDTO;

/**
 *
 * @author ADMINS
 */
public class SearchResult extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search Result</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search Result</h1>");
            
            
            
            String searchValue = request.getParameter("txtSearchValue");
            out.println("Your search value is " + searchValue);
            
            out.println("<br>");
            out.println("<br>");
            
            List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCHRESULT");
            if (result != null) {
                out.println("<table border = '1'>");
                
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>No.</th>");
                out.println("<th>Username</th>");
                out.println("<th>Password</th>");
                out.println("<th>LastName</th>");
                out.println("<th>Role</th>");
                out.println("<th>Delete</th>");
                out.println("<th>Update</th>");
                out.println("</tr>");
                out.println("</thead>");
               
                out.println("<tbody>");
                int count = 0;
                String checked;
                for (RegistrationDTO x : result) {
                    checked=null;
                    if(x.isRole()) checked = "checked";
                    out.println("<tr>");
                    out.println("<form action='UpdateController'>");
                    out.println("<td>" + ++count + "</td>");
                    out.println("<td>" 
                            + x.getUsername() 
                            + "<input type='hidden' name='txtUsername' value='" + x.getUsername() + "'/>"
                            + "</td>");
                    out.println("<td>" 
                            + "<input type='text' name='txtPassword' value='" + x.getPassword() + "'/>" 
                            + "</td>");
                    out.println("<td>" 
                            + x.getLastname() 
                            + "</td>");                  
                    out.println("<td>" 
                            + "<input type='checkbox' name='chkRole' value='ADMIN'" + checked + "/>"  
                            + "</td>");
                    out.println("<td>" 
                            + "<a href=DeleteController?txtSearchValue=" + searchValue + "&username=" + x.getUsername() + ">Delete</a>" 
                            + "</td>");
                    out.println("<td>" 
                            + "<input type='hidden' name='txtSearchValue' value='" + searchValue + "'/>" 
                            + "<input type='submit' name='btAction' value='Update'/>" 
                            + "</td>");
                    out.println("</form>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                
                out.println("</table>");
                
                out.println("<br>");
                
                out.println("<form action='welcome.html'>");
                out.println("<input type=submit value='Return'/>");
                out.println("</form>");
                
                out.println("<br>");
                out.println("<br>");
                
                if (request.getAttribute("UpdateStatus") != null) {
                    String updateStatus = (String)request.getAttribute("UpdateStatus");
                    out.println(updateStatus);
                }
                
                if (request.getAttribute("DeleteResult") != null) {
                    int row = (int)request.getAttribute("DeleteResult");
                    out.println(row + " row(s) were affected.");
                }
            }
            else {
                out.println("<h2>No record is matched</h2>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
