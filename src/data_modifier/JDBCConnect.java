/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import java.sql.*;

/**
 *
 * @author sa
 */
public abstract class JDBCConnect {

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SIMS", "sa", "123456");
    }
    
}
