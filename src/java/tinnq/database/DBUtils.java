/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinnq.database;

import java.io.Serializable;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author admin
 */
public class DBUtils implements Serializable {
    public static Connection makeConnection() throws NamingException {
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=TinNQSP23";
//            Connection con = DriverManager.getConnection(url, "sa", "12345");
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("TinNQSP23");
            Connection con = ds.getConnection();
            return con;
        }
//        catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
        catch (NamingException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
