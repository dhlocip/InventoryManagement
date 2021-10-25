/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.ImportStockDetail;
import static data_modifier.JDBCConnect.connect;
import java.sql.*;

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
        preS.setString(1, importStockDetail.getImportStockId());
        preS.setString(2, importStockDetail.getProductId());
        preS.setInt(3, importStockDetail.getQuantity());
        preS.setDouble(4, importStockDetail.getPrice());
        preS.setString(5, importStockDetail.getMfgDate());
        preS.setString(6, importStockDetail.getExpDate());
        preS.execute();
        return true;
    }
    // update ImportStockDetail
    public boolean updateImportStock(ImportStockDetail importStockDetail) throws SQLException{
        String sql = "update importStockDetail "
                + "set quantity =?, price =?, mfgDate =?, expDate =? "
                + "where importStockId =? and productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setInt(1, importStockDetail.getQuantity());
        preS.setDouble(2, importStockDetail.getPrice());
        preS.setString(3, importStockDetail.getMfgDate());
        preS.setString(4, importStockDetail.getExpDate());
        preS.setString(5, importStockDetail.getImportStockId());
        preS.setString(6, importStockDetail.getProductId());
        preS.executeUpdate();
        return true;
    }
    
    // check productId is exists
    public boolean productIdIsExists(String productId, String importStockId) throws SQLException{
        String sql = "select * from importStockDetail "
                + "where productId =? and importStockId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, productId);
        preS.setString(2, importStockId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            return true;
        }
        return false;
    }
    
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
    
//    public static void main(String[] args) throws SQLException {
//        ImportStockDetail imp = new ImportStockDetail("i0009", "p0004", 201, 100000, "01/01/2021", "01/01/2023");
//        new ImportStockDetailModifier().updateImportStock(imp);
//        
//    }
}
