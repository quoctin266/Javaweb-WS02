/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinnq.registration;

import tinnq.database.DBUtils;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author admin
 */
public class RegistrationDAO implements Serializable {
    public boolean checkLogin (String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select * from registration "
                        + "where username = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }
    
    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
               String sql = "select * from registration where lastname like ?";
               if (searchValue.length() == 0) sql = "select * from registration";
               stm = con.prepareStatement(sql);
               if (searchValue.length() != 0) {
                   stm.setString(1, "%" + searchValue + "%");
               }
               rs = stm.executeQuery();
               while (rs.next()) {
                   String username = rs.getString("username");
                   String password = rs.getString("password");
                   String lastname = rs.getString("lastname");
                   boolean role = rs.getBoolean("isAdmin");
                   
                   RegistrationDTO dto = new RegistrationDTO(username,password,lastname,role);
                   
                   if (this.listAccounts == null) {
                       this.listAccounts = new ArrayList<>();
                   }
                   this.listAccounts.add(dto);
               }
            }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public int deleteEntry(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        int row = 0;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
               String sql = "delete from registration where username = ?";
               stm = con.prepareStatement(sql);
               stm.setString(1, username);
               row = stm.executeUpdate();
            }
        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return row;
    }
    
    public boolean updateInfo(String username, String password, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
               String sql = "update registration set password = ?, isAdmin = ? where username = ?";
               stm = con.prepareStatement(sql);
               stm.setString(1, password);
               stm.setBoolean(2, role);
               stm.setString(3, username);
               int row = stm.executeUpdate();
               if (row > 0) return true;
            }
        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
     public boolean insertRecord(String username, String password, String lastname, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
               String sql = "insert into registration(username,password,lastname,isAdmin) values (?,?,?,?)";
               stm = con.prepareStatement(sql);
               stm.setString(1, username);
               stm.setString(2, password);
               stm.setString(3, lastname);
               stm.setBoolean(4, role);
               int row = stm.executeUpdate();
               if (row > 0) return true;
            }
        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
