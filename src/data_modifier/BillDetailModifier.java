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
public class BillDetailModifier extends JDBCConnect {
    //    delete bill detail by billId
    public boolean deleteBillDetail(String billIdOrProductId) throws SQLException {
        String sql = "delete from billDetail "
                + "where billId =? or productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, billIdOrProductId);
        preS.setString(2, billIdOrProductId);
        preS.execute();
        return true;
    }
}
