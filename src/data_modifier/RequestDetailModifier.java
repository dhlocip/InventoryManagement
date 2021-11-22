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
public class RequestDetailModifier {

    //    delete request detail by requestId
    public boolean deleteRequestDetail(String requestIdOrProductId) throws SQLException {
        String sql = "delete from requestDetail "
                + "where requestId =? or productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, requestIdOrProductId);
        preS.setString(2, requestIdOrProductId);
        preS.execute();
        return true;
    }
}
