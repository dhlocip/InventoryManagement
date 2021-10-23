/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import static data_modifier.JDBCConnect.connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class ImportStockModifier extends JDBCConnect {

    //  get list importStockId
    public ObservableList<String> getListImportStockId(String userId) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from ImportStocks "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(result.getString("importStockId"));
        }
        return oList;
    }

    //    delete import Stock detail by importStockId
    public boolean deleteImportStockDetail(String importStockIdOrProductId) throws SQLException {
        String sql = "delete from importStockDetail "
                + "where importStockId =? or productId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, importStockIdOrProductId);
        preS.setString(2, importStockIdOrProductId);
        preS.execute();
        return true;
    }

    //    delete importStocks by userId
    public boolean deleteImportStock(String userId) throws SQLException {
        String sql = "delete from importStocks "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }
}
