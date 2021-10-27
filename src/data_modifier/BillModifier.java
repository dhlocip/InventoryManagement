/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;



import data.BillStatistic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author ADMIN
 */
public class BillModifier extends JDBCConnect {
    
    public ObservableList<BillStatistic> getBillSuccessfulInfo() throws SQLException{
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
        String sql = "select * from BillStatistic where statusCancel = ''";
        PreparedStatement preparedStatement = connect().prepareStatement(sql);
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("billId"),result.getString("userId"),
                    result.getString("transactionDate"),result.getString("statusCancel"),
                    result.getString("paymentName"),result.getString("productId"),
                    result.getInt("quantity"),result.getString("mfgDate"),result.getString("expDate"),
                    result.getFloat("price"),result.getFloat("total")));
        }
        return oList;
    }
    
    public ObservableList<BillStatistic> getBillCancelInfo() throws SQLException{
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
        String sql = "select * from BillStatistic where statusCancel = 'YES'";
        PreparedStatement preparedStatement = connect().prepareStatement(sql);
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("billId"),result.getString("userId"),
                    result.getString("transactionDate"),result.getString("statusCancel"),
                    result.getString("paymentName"),result.getString("productId"),
                    result.getInt("quantity"),result.getString("mfgDate"),result.getString("expDate"),
                    result.getFloat("price"),result.getFloat("total")));
        }
        return oList;
    }
    public ObservableList<String> getListTransactionDate(LocalDate transactionDate) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from Bills where transactionDate like '%" + transactionDate + "%'";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("transactionDate"));
        }
        return oList;
    }

 
   
    
   
    
    // ---------------
    
    //  get list billId
    public ObservableList<String> getListBillId(String userId) throws SQLException{
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from bills "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(result.getString("billId"));
        }
        return oList;
    }
    
    //    delete bill detail by billId
    public boolean deleteBillDetail(String billIdOrProductId) throws SQLException{
        String sql = "delete from billDetail "
                + "where billId =? or productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, billIdOrProductId);
        preS.setString(2, billIdOrProductId);
        preS.execute();
        return true;
    }
    
    //    delete bill by userId
    public boolean deleteBill(String userId) throws SQLException{
        String sql = "delete from bills "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }
    
    
}


