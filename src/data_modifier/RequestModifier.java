/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import data.VRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author ADMIN
 */
public class RequestModifier {
    
     public Connection connect() throws SQLException{
       return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SIMS","sa","123456");
    }
    
    public ObservableList<VRequest> getRequestInfo() throws SQLException{
    ObservableList<VRequest> oList = FXCollections.observableArrayList();
    String sql = "Select * from VRequest"; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VRequest(result.getString("requestId"),result.getString("userId"), 
                    result.getString("startDate"), result.getString("statusVerify")
                    , result.getString("productId"), result.getInt("quantity"))); //tencotsql
        }
        return oList;
    }
    
    
}
