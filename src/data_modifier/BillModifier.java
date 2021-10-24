/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_modifier;



import data.BillDetail;
import data.BillStatistic;
import data.Bills;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author ADMIN
 */
public class BillModifier extends JDBCConnect {
    
    
    public ObservableList<String> getListTransactionDate(String transactionDate) throws SQLException {
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

    public  ObservableList<Bills> getInfoBills() throws SQLException{
        ObservableList<Bills> oList = FXCollections.observableArrayList();
        String sql = "select * from Bills";
        PreparedStatement preparedStatement = connect().prepareStatement(sql);
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
        while (result.next()) {
            oList.add(new Bills(result.getString("billId"), result.getString("userId"), result.getString("transactionDate")
                    , result.getString("statusCancel"), result.getString("paymentName")));
            
        }
        return oList;
    
    }
    public  ObservableList<BillDetail> getInfoBillDetail() throws SQLException{
        ObservableList<BillDetail> oList = FXCollections.observableArrayList();
        String sql = "select * from BillDetail";
        PreparedStatement preparedStatement = connect().prepareStatement(sql);
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillDetail(result.getString("productId"), result.getString("billId"), result.getInt("quantity")
                    , result.getFloat("price"), result.getString("mfgDate"),result.getString("expDate")));
            
        }
        return oList;
    
    }
    
 

    
    public ObservableList<BillStatistic> getInfoByCancel() throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
    String sql = "Select * from BillStatistic where  statusCancel != '' "; 
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("productId"), result.getString("billId"), 
                    result.getInt("quantity"), result.getFloat("price"), result.getString("mfgDate"),
                    result.getString("expDate"), result.getString("userId"),
                    result.getString("transactionDate"), result.getString("statusCancel"), result.getString("paymentName"),
            result.getFloat("total"),result.getFloat("revenue"),result.getFloat("totalCancel")));
        }
        return oList;
    }
    
    public ObservableList<BillStatistic> getInfoByDate() throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
    String sql = "Select * from BillStatistic where  statusCancel = '' "; 
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("productId"), result.getString("billId"), 
                    result.getInt("quantity"), result.getFloat("price"), result.getString("mfgDate"),
                    result.getString("expDate"), result.getString("userId"),
                    result.getString("transactionDate"), result.getString("statusCancel"), result.getString("paymentName"),
            result.getFloat("total"),result.getFloat("revenue"),result.getFloat("totalCancel")));
        }
        return oList;
    }
    
    public ObservableList<BillStatistic> getInfoByCancelByDate() throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
    String sql = "Select * from BillStatistic where  statusCancel != '' "; 
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("productId"), result.getString("billId"), 
                    result.getInt("quantity"), result.getFloat("price"), result.getString("mfgDate"),
                    result.getString("expDate"), result.getString("userId"),
                    result.getString("transactionDate"), result.getString("statusCancel"), result.getString("paymentName"),
            result.getFloat("total"),result.getFloat("revenue"),result.getFloat("totalCancel")));
        }
        return oList;
    }  
    
    public ObservableList<BillStatistic> getTotalCancelByDate(String startDate, String endDate) throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
    String sql = "Select Sum(total) as sumTotal from BillStatistic where  statusCancel = 'Yes' and (transactionDate between ? and ?"; 
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.setString(1, startDate);
    preStatement.setString(2, endDate);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("productId"), result.getString("billId"), 
                    result.getInt("quantity"), result.getFloat("price"), result.getString("mfgDate"),
                    result.getString("expDate"), result.getString("userId"),
                    result.getString("transactionDate"), result.getString("statusCancel"), result.getString("paymentName"),
            result.getFloat("total"),result.getFloat("revenue"),result.getFloat("totalCancel")));
        }
        return oList;
    }  
    
    public ObservableList<BillStatistic> getTotalRevenueByDate(String startDate, String endDate) throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
    String sql = "Select Sum(total) as sumTotal from BillStatistic where statusCancel = '' and (transactionDate between ? and ?"; 
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.setString(1, startDate);
    preStatement.setString(2, endDate);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("productId"), result.getString("billId"), 
                    result.getInt("quantity"), result.getFloat("price"), result.getString("mfgDate"),
                    result.getString("expDate"), result.getString("userId"),
                    result.getString("transactionDate"), result.getString("statusCancel"), result.getString("paymentName"),
            result.getFloat("total"),result.getFloat("revenue"),result.getFloat("totalCancel")));
        }
        return oList;
    }  
   
    
}

