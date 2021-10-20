/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;

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

    public ObservableList<Events> getDEventsInfo() throws SQLException {
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
                    result.getString("discount"), result.getString("mfdDate"), result.getString("expDate"))); //tencotsql
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

}
