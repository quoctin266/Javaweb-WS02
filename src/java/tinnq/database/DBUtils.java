/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinnq.database;

import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author admin
 */
public class DBUtils implements Serializable {
    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=TinNQSP23";
            Connection con = DriverManager.getConnection(url, "sa", "12345");
            return con;
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
