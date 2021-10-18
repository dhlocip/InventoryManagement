/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import data.VNewRequest;
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
public class NewRequestModilfier extends JDBCConnect {

    
    public ObservableList<VNewRequest> getNewRequestInfo() throws SQLException{
    ObservableList<VNewRequest> oList = FXCollections.observableArrayList();
    String sql = "Select * from VNewRequest"; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VNewRequest(result.getString("newRequestId"),result.getString("userId"), 
                    result.getString("startDate"), result.getString("statusVerify")
                    , result.getString("newProductName"), result.getInt("quantity"))); //tencotsql
        }
        return oList;
}
}
