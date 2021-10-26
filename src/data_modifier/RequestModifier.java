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
import java.util.List;
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
    
    public boolean getRequestUpdate(String newStatusVerify, String requestID) throws SQLException{
    String sql = "Update Requests set statusVerify = ? where requestId = ? "; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    
    preStatement.setString(1, newStatusVerify);
    preStatement.setString(2, requestID);
    
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
    
    public ObservableList<String> getNumberRequest() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select count(DISTINCT requestId) as NumberRequest from Requests where statusVerify = ''";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("NumberRequest"));
        }
        return oList;

    // ---------------
    
    //  get list request
    public ObservableList<String> getListRequestId(String userId) throws SQLException{
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from requests "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(result.getString("requestId"));
        }
        return oList;
    }
    
    //    delete request detail by requestId
    public boolean deleteRequestDetail(String requestIdOrProductId) throws SQLException{
        String sql = "delete from requestDetail "
                + "where requestId =? or productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, requestIdOrProductId);
        preS.setString(2, requestIdOrProductId);
        preS.execute();
        return true;
    }
    
    //    delete requests by userId
    public boolean deleteRequest(String userId) throws SQLException{
        String sql = "delete from requests "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }
    
}
