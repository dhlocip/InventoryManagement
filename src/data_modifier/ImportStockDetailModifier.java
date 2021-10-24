/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.ImportStockDetail;
import static data_modifier.JDBCConnect.connect;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sa
 */
public class ImportStockDetailModifier extends JDBCConnect {
    
    // create ImportStockDetail
    public boolean createImportStock(ImportStockDetail importStockDetail) throws SQLException{
        String sql = "insert into importStockDetail (importStockId, productId, quantity, price, mfgDate, expDate) "
                + "values (?,?,?,?,?,?)";
        PreparedStatement preS = connect().prepareStatement(sql);
//        preS.setString(1, importStock.getUserId());
//        preS.setString(2, importStock.getSupplierId());
//        preS.setString(3, importStock.getImportDate());
        preS.execute();
        return true;
    }
    // update ImportStockDetail
    
    //    delete import Stock detail by importStockId or productId
    public boolean deleteImportStockDetail(String importStockIdOrProductId) throws SQLException {
        String sql = "delete from importStockDetail "
                + "where importStockId =? or productId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, importStockIdOrProductId);
        preS.setString(2, importStockIdOrProductId);
        preS.execute();
        return true;
    }
    
}
