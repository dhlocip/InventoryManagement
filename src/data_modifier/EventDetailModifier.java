/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import static data_modifier.JDBCConnect.connect;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ip
 */
public class EventDetailModifier {

    //    delete event detail by eventId
    public boolean deleteEventDetail(String eventIdOrProductId) throws SQLException {
        String sql = "delete from eventDetail "
                + "where eventId =? or productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, eventIdOrProductId);
        preS.setString(2, eventIdOrProductId);
        preS.execute();
        return true;
    }
}
