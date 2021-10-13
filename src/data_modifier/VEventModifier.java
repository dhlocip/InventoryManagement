/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import data.VEvent;
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
public class VEventModifier {
    
    public Connection connect() throws SQLException{
       return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SIMS","sa","123456");
    }
    
    public ObservableList<VEvent> getInfo() throws SQLException{
    ObservableList<VEvent> oList = FXCollections.observableArrayList();
    String sql = "Select * from VEvent"; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VEvent(result.getString("eventId"),result.getString("userId"),result.getString("eventName"), 
                    result.getString("startDate"), result.getString("endDate")
                    , result.getString("productId"), result.getString("discount"), result.getString("mfgDate"), 
                    result.getString("expDate"))); //tencotsql
        }
        return oList;
    }
    
    
//    public static void main(String[] args) throws SQLException {
//        ObservableList<VEvent> oList = new VEventModifier().getInfo();
//        for (VEvent vEvent : oList) {
//            System.out.println(vEvent.getEventName());
//        }
//        ++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
//        
        
//    }
    
}
