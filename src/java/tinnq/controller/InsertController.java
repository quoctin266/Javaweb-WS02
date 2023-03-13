/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tinnq.registration.RegistrationDAO;
import tinnq.registration.RegistrationDTO;
import tinnq.registration.RegistrationInsertError;

/**
 *
 * @author admin
 */
public class InsertController extends HttpServlet {
    private final String CREATENEWRESULT = "createNewAccount.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            String url = CREATENEWRESULT;
            try {
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                String confPassword = request.getParameter("txtConfirm");
                String fullname = request.getParameter("txtFullname");
                String usernameLengthErr = null;
                String passwordLengthErr = null;
                String confirmNotMatch = null;
                String fullnameLengthErr = null;
                String usernameExisted = null;
                
                RegistrationDAO dao = new RegistrationDAO();
                dao.searchLastname("");
                List<RegistrationDTO> result = dao.getListAccounts();
                for (RegistrationDTO x : result) {
                    if (x.getUsername().equals(username)) {
                        usernameExisted = "Username already taken!";
                    }
                }
                if (username.length()<6 || username.length()>20) {
                    usernameLengthErr = "Must be beetween 6-20 characters!";
                }
                if (password.length()<6 || password.length()>30) {
                    passwordLengthErr = "Must be beetween 6-30 characters!";
                }
                if (!confPassword.equals(password)) {
                    confirmNotMatch = "Password confirm does not match!";
                }
                if (fullname.length()<2 || password.length()>50) {
                    fullnameLengthErr = "Must be beetween 2-50 characters!";
                }
                RegistrationInsertError error = new RegistrationInsertError(usernameLengthErr,passwordLengthErr,
                        confirmNotMatch,fullnameLengthErr,usernameExisted);
                request.setAttribute("INSERTERR", error);
                if (usernameLengthErr == null && passwordLengthErr == null && confirmNotMatch == null 
                        && fullnameLengthErr == null && usernameExisted == null ) {
                    dao.insertRecord(username, password, fullname, false);
                    url = "index.html";
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
            }            
            finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
