/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;


import data.VNewRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class NewRequestModilfier extends JDBCConnect {

    public ObservableList<VNewRequest> getNewRequestInfo() throws SQLException {
        ObservableList<VNewRequest> oList = FXCollections.observableArrayList();
        String sql = "Select * from VNewRequest"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VNewRequest(result.getString("newRequestId"), result.getString("userId"),
                    result.getString("startDate"), result.getString("statusVerify"),
                    result.getString("newProductName"), result.getInt("quantity"))); //tencotsql
        }
        return oList;
    }
    
    public boolean getNewRequestUpdate(String newStatusVerify, String newNewRequestId) throws SQLException{
    String sql = "Update NewRequests set statusVerify = ? where newRequestId = ? "; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.setString(1, newStatusVerify);
    preStatement.setString(2, newNewRequestId);
    preStatement.execute();

        return true;
    }
    // so newrequest chua duyet
     public ObservableList<String> getNumberNewRequest() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select count(newRequestId) as NumberNewRequest from NewRequests where statusVerify = ''";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("NumberNewRequest"));
        }
        return oList;
    }

    // ---------------
    //  get list newRequest
    public ObservableList<String> getListNewRequestId(String userId) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from newRequests "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(result.getString("newRequestId"));
        }
        return oList;
    }

    //    delete newRequest detail by newRequestId
    public boolean deleteNewRequestDetail(String newRequestId) throws SQLException {
        String sql = "delete from newRequestDetail "
                + "where newRequestId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, newRequestId);
        preS.execute();
        return true;
    }

    //    delete newRequests by userId
    public boolean deleteNewRequest(String userId) throws SQLException {
        String sql = "delete from newRequests "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }
}
