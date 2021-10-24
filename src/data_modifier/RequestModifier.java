/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import data.Request;
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
    
    public boolean getRequestUpdate(String newStatusVerify, String newProductId) throws SQLException{
    String sql = "Update Requests set statusVerify = ? where productId = ? "; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.setString(1, newStatusVerify);
    preStatement.setString(2, newProductId);
    preStatement.execute();
    
        return true;
    }
    
     public boolean getNewRequestUpdate(String newStatusVerify, String newProductName) throws SQLException{
    String sql = "Update NewRequests set statusVerify = ? where productName = ? "; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.setString(1, newStatusVerify);
    preStatement.setString(2, newProductName);
    preStatement.execute();
    
        return true;
    }
    
    
//    public boolean updateUser(Users users) throws SQLException{
//        String sql = "update users "
//                + "set phone = ?, fullName = ?, gender = ?, dateOfBirth = ?, address = ?, position = ?, email = ? "
//
//                + "where userId =?";
//        PreparedStatement preStatement = connect().prepareStatement(sql);
//        preStatement.setString(1, users.getPhone());
//        preStatement.setString(2, users.getFullName());
//        preStatement.setString(3, users.getGender());
//        preStatement.setString(4, users.getDateOfBirth());
//        preStatement.setString(5, users.getAddress());
//        preStatement.setString(6, users.getPosition());
//        preStatement.setString(7, users.getEmail());
//        preStatement.setInt(8, users.getUserId());
//        preStatement.executeUpdate();
//        return true;
//    }
//    
}
