/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.VImportStock;
import static data_modifier.JDBCConnect.connect;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class VImportStockModifier extends JDBCConnect {

//  get list importStock info
    public ObservableList<VImportStock> getListVImportStock() throws SQLException {
        ObservableList<VImportStock> oList = FXCollections.observableArrayList();
        String sql = "select * from VImportStock";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new VImportStock(result.getString("importStockId"), result.getString("supplierId"),
                    result.getString("importDate"), result.getString("productId"), result.getString("quantity"),
                    result.getString("price"), result.getString("mfgDate"), result.getString("expDate")));
        }
        return oList;
    }

//  get list importStock info after search
    public ObservableList<VImportStock> getListVImportStockAfterSearch(String idOrDate) throws SQLException {
        ObservableList<VImportStock> oList = FXCollections.observableArrayList();
        String sql = "select * from VImportStock "
                + "where importStockId like '%" + idOrDate + "%' or "
                + "supplierId like '%" + idOrDate + "%' or "
                + "format(importDate,'MM/dd/yyy') =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, idOrDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new VImportStock(result.getString("importStockId"), result.getString("supplierId"),
                    result.getString("importDate"), result.getString("productId"), result.getString("quantity"),
                    result.getString("price"), result.getString("mfgDate"), result.getString("expDate")));
        }
        return oList;
    }
}
