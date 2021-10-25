/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import data.Request;
import data.VRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author ADMIN
 */
public class RequestModifier extends JDBCConnect {
    
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
    
    public boolean getRequestUpdate(String newStatusVerify, String userID) throws SQLException{
    String sql = "Update Requests set statusVerify = ? where userId = ? "; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    
    preStatement.setString(1, newStatusVerify);
    preStatement.setString(2, userID);
    
    preStatement.executeUpdate();
        return true;
    
       
    }
    
    public ObservableList<Request> getRequestCount() throws SQLException{
    ObservableList<Request> oList = FXCollections.observableArrayList();
    String sql = " select COUNT(statusVerify) from Requests where  statusVerify = '' "; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new Request(result.getString("requestId"),result.getString("userId"), 
                    result.getString("startDate"), result.getString("statusVerify")));
            
        }
        return oList;
    
       
    }
    
  
}
