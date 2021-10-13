/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class DatabaseHelper {
    
   
    public static Connection openConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=SIMS";
        String dbusername ="sa";
        String password = "123456";
        Connection con = DriverManager.getConnection(connectionUrl,dbusername, password);
        return con;
    }    
}
