/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import static data_modifier.JDBCConnect.connect;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class EventModifier extends JDBCConnect {
    
    //  get list eventId
    public ObservableList<String> getListEventId(String userId) throws SQLException{
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from events "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(result.getString("eventId"));
        }
        return oList;
    }
    
    //    delete event by userId
    public boolean deleteEvent(String userId) throws SQLException{
        String sql = "delete from events "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }
    
}
