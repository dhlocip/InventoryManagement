/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;


import data.VBills;
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
    
    public ObservableList<VBills> getVBillsInfo() throws SQLException{
    ObservableList<VBills> oList = FXCollections.observableArrayList();
    String sql = "Select * from VBills"; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VBills(result.getString("billId"),result.getString("userId"),result.getDate("transactionDate") ,
                    result.getString("statusCancel"),result.getString("paymentName"),result.getString("productId"), 
                    result.getInt("quantity"),result.getString("mfgDate"),result.getString("expDate"), 
                    result.getFloat("price"),result.getFloat("total")/*,result.getFloat("revenue"), 
                    result.getInt("numberBills"),result.getFloat("totalCancel")*/)); //tencotsql
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

 
    public ObservableList<VBills> getInfoByTransactionDate(LocalDate transactiondate) throws SQLException {
        ObservableList<VBills> oList = FXCollections.observableArrayList();
        String sql;
        PreparedStatement preStatement;
        sql = "select * from VBills "
                + " where transactionDate like '%" + transactiondate + "%'";
        preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VBills(result.getString("billId"),result.getString("userId"),result.getDate("transactionDate") ,
                    result.getString("statusCancel"),result.getString("paymentName"),result.getString("productId"), 
                    result.getInt("quantity"),result.getString("mfgDate"),result.getString("expDate"), 
                    result.getFloat("price"),result.getFloat("total")/*,result.getFloat("revenue"), 
                    result.getInt("numberBills"),result.getFloat("totalCancel")*/)); //tencotsql
        }
        return oList;
    }
    
    public ObservableList<VBills> getInfoByCancel() throws SQLException {
        ObservableList<VBills> oList = FXCollections.observableArrayList();
    String sql = "Select * from VBills where  statusCancel = 'Yes' "; 
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VBills(result.getString("billId"),result.getString("userId"),result.getDate("transactionDate") ,
                    result.getString("statusCancel"),result.getString("paymentName"),result.getString("productId"), 
                    result.getInt("quantity"),result.getString("mfgDate"),result.getString("expDate"), 
                    result.getFloat("price"),result.getFloat("total")/*,result.getFloat("revenue"), 
                    result.getInt("numberBills"),result.getFloat("totalCancel")*/)); //tencotsql
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


