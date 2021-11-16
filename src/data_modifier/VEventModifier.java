
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

import data.EventDetail;
import data.Events;
import data.VEvent;
import static data_modifier.JDBCConnect.connect;
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
public class VEventModifier extends JDBCConnect {

    public ObservableList<VEvent> getVEventInfo() throws SQLException {
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
        String sql = "select eventId from Events";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("eventId"));
        }
        return oList;
    }
    public ObservableList<String> getListStartEvent() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select startDate from Events";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("startDate"));
        }
        return oList;
    }
    
    public ObservableList<String> getListEventName() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select DISTINCT eventName from VEvent";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("eventName"));
        }
        return oList;
    }
    
    public ObservableList<String> getListEventIdDate(String endEvent) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select eventId from Events where endDate >= ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, endEvent);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("eventId"));
        }
        return oList;
    }
    
    

    public ObservableList<String> getListProductId() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select DISTINCT productId from Products ";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("productId"));
        }
        return oList;
    }

    public ObservableList<String> getListProductIdEvent() throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select productId from EventDetail";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("productId"));
        }
        return oList;
    }

    
    
    public ObservableList<String> getProductIdEvent(String endEvent) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select productId from Products EXCEPT select productId from VEvent where endDate >= ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, endEvent);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("productId"));
        }
        return oList;
    }

    public ObservableList<VEvent> getInfoByEventId(String eventId) throws SQLException {
        ObservableList<VEvent> oList = FXCollections.observableArrayList();
        String sql = "select * from VEvent "
                + " where eventId like '%" + eventId + "%'";
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

    public boolean getCreateEvents(Events events) throws SQLException {
        String sql = "insert into Events (userId, eventName, startDate, endDate) VALUES(?,?,?,?)"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, events.getUserId());
        preStatement.setString(2, events.getEventName());
        preStatement.setString(3, events.getStartDate());
        preStatement.setString(4, events.getEndDate());
        preStatement.execute();
        return true;

    }

    public boolean getCreateEventDetail(EventDetail eventDetail) throws SQLException {
        String sql = "insert into EventDetail (eventId, productId, discount, mfgDate, expDate) VALUES(?,?,?,?,?)"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, eventDetail.getEventId());
        preStatement.setString(2, eventDetail.getProductId());
        preStatement.setString(3, eventDetail.getDiscount());
        preStatement.setString(4, eventDetail.getMfgDate());
        preStatement.setString(5, eventDetail.getExpDate());
        preStatement.execute();
        return true;

    }

    public boolean getUpdateEventDetail(EventDetail eventDetail) throws SQLException {

        String sql = "update eventDetail set eventId= ?, discount = ?, mfgDate =?, expDate =?   where productId = ? ";
        PreparedStatement preStatement = connect().prepareStatement(sql);

        preStatement.setString(1, eventDetail.getEventId());
        preStatement.setString(2, eventDetail.getDiscount());
        preStatement.setString(3, eventDetail.getMfgDate());
        preStatement.setString(4, eventDetail.getExpDate());
        preStatement.setString(5, eventDetail.getProductId());
        preStatement.executeUpdate();
        return true;
    }

    public boolean getUpdateEvent(Events events) throws SQLException {
        String sql = "update events set eventName = ?, startDate = ?, endDate =?  where eventId = ? ";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, events.getEventName());
        preStatement.setString(2, events.getStartDate());
        preStatement.setString(3, events.getEndDate());
        preStatement.setString(4, events.getEventId());
        preStatement.executeUpdate();
        return true;
    }

  
}
