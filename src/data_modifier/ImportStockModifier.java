/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.ImportStock;
import static data_modifier.JDBCConnect.connect;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class ImportStockModifier extends JDBCConnect {

    //  get list importStockId from importStocks
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
    
    // create ImportStocks
    public boolean createImportStock(ImportStock importStock) throws SQLException{
        String sql = "insert into importStocks (userId, supplierId, importDate) "
                + "values (?,?,?)";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, importStock.getUserId());
        preS.setString(2, importStock.getSupplierId());
        preS.setString(3, importStock.getImportDate());
        preS.execute();
        return true;
    }
    
    // update ImportStocks
    public boolean updateImportStock(ImportStock importStock) throws SQLException{
        String sql = "update importStocks "
                + "set supplierId = ?, importDate =? "
                + "where importStockId =? and userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, importStock.getSupplierId());
        preS.setString(2, importStock.getImportDate());
        preS.setString(3, importStock.getImportStockId());
        preS.setString(4, importStock.getUserId());
        preS.execute();
        return true;
    }

    //    delete importStocks by userId or importStockId
    public boolean deleteImportStock(String userIdOrImportStockId) throws SQLException {
        String sql = "delete from importStocks "
                + "where userId =? or importStockId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userIdOrImportStockId);
        preS.setString(2, userIdOrImportStockId);
        preS.execute();
        return true;
    }
    
    // get max importStockId
    public String getMaxImportStockId() throws SQLException{
        String maxImportStockId = null;
        String sql = "select max(importStockId) as maxImportStockId "
                + "from ImportStocks";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            maxImportStockId = result.getString("maxImportStockId");
        }
        return maxImportStockId;
    }
    
//    public static void main(String[] args) throws SQLException {
//         var a = new ImportStockModifier().getMaxImportStockId();
//         System.out.println(a);
//    }
}
