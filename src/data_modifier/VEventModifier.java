/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import controller_app.UIDashboardSaleManagerController;
import data.BillDetail;
import data.EventDetail;
import data.Events;
import data.VEvent;
import static data_modifier.JDBCConnect.connect;
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
public class VEventModifier extends JDBCConnect {

    public ObservableList<VEvent> getEventInfo() throws SQLException {
        ObservableList<VEvent> oList = FXCollections.observableArrayList();
        String sql = "Select * from VEvent"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VEvent(result.getString("eventId"), result.getString("userId"), result.getString("eventName"),
                    result.getString("startDate"), result.getString("endDate"),
                    result.getString("productId"), result.getString("discount"), result.getString("mfgDate"),
                    result.getString("expDate"))); //tencotsql
        }
        return oList;
    }

    public ObservableList<Events> getVEventsInfo() throws SQLException {
        ObservableList<Events> oList = FXCollections.observableArrayList();
        String sql = "Select * from Events"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new Events(result.getString("eventId"), result.getString("userId"), result.getString("eventName"),
                    result.getString("startDate"), result.getString("endDate"))); //tencotsql
        }
        return oList;
    }

    public ObservableList<EventDetail> getEventDetailInfo() throws SQLException {
        ObservableList<EventDetail> oList = FXCollections.observableArrayList();
        String sql = "Select * from EventDetail"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new EventDetail(result.getString("eventId"), result.getString("productId"),
                    result.getString("discount"), result.getString("mfgDate"), result.getString("expDate"))); //tencotsql
        }
        return oList;
    }

    public boolean deleteEventDetailByEventId(String eventId) throws SQLException {
        String sql = "delete from eventDetail "
                + "where eventId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, eventId);
        preStatement.execute();
        return true;
    }

    public boolean deleteEventByEventId(String eventId) throws SQLException {
        String sql = "delete from events "
                + "where eventId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, eventId);
        preStatement.execute();
        return true;
    }

    public ObservableList<String> getListEventId() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from Events";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("eventId"));
        }
        return oList;
    }

    
    public ObservableList<String> getListProductId() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select productId from Products";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("productId"));
        }
        return oList;
    }
    
    public ObservableList<VEvent> getInfoByEventId(String eventId) throws SQLException {
        ObservableList<VEvent> oList = FXCollections.observableArrayList();
        String sql;
        PreparedStatement preStatement;

        sql = "select * from VEvent "
                + " where eventId like '%" + eventId + "%'";
        preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VEvent(result.getString("eventId"), result.getString("userId"), result.getString("eventName"),
                    result.getString("startDate"), result.getString("endDate"),
                    result.getString("productId"), result.getString("discount"), result.getString("mfgDate"),
                    result.getString("expDate"))); //tencotsql
        }
        return oList;
    }

        public ObservableList<Events> getCreateEvent(Events events) throws SQLException {
        String sql = "insert into Events VALUES(?,?,?,?)"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        
        preStatement.setString(2, events.getEventName());
        preStatement.setString(3, events.getStartDate());
        preStatement.setString(4, events.getEndDate());
        
        preStatement.executeUpdate();
        return null;
        
    }
        public ObservableList<EventDetail> getCreateEvent(EventDetail eventDetail) throws SQLException {
        String sql = "insert into EventDetails VALUES(?,?,?)"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        
        preStatement.setString(1, eventDetail.getEventId());
        preStatement.setString(2, eventDetail.getProductId());
        preStatement.setString(3, eventDetail.getDiscount());
        preStatement.executeUpdate();
        return null;
        
    }
        
        
        public boolean getUpdateEventDetail(EventDetail eventDetail) throws SQLException {
        String sql = "update eventDetail set productId= ?, discount = ?  where eventId = ? ";
        PreparedStatement preStatement = connect().prepareStatement(sql);

        preStatement.setString(1, eventDetail.getProductId());
        preStatement.setString(2, eventDetail.getDiscount());
        

        preStatement.executeUpdate();
        return true;
    }
        
        public boolean getUpdateEvent(Events events) throws SQLException {
        String sql = "update events set p, discount = ?  where eventId = ? ";
        PreparedStatement preStatement = connect().prepareStatement(sql);

        preStatement.setString(1, events.getEventName());
        preStatement.setString(2, events.getStartDate());
        preStatement.setString(3, events.getEndDate());
        

        preStatement.executeUpdate();
        return true;
    }
    
}

