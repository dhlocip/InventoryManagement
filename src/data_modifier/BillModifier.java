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
import javafx.scene.chart.XYChart;

/**
 *
 * @author ADMIN
 */
public class BillModifier extends JDBCConnect {

    public ObservableList<BillStatistic> getInfByDate() throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
        String sql = "Select * from BillStatistic where statusCancel = ''"; //viewsql
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("billId"), result.getString("userId"),
                    result.getDate("transactionDate"), result.getString("paymentName"),
                    result.getString("statusCancel"), result.getString("quantity"),
                    result.getInt("quantity"), result.getString("mfgDate"), result.getString("expDate"),
                    result.getFloat("price"), result.getFloat("total")));
        }
        return oList;
    }

    public ObservableList<BillStatistic> getInfoByCancel() throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
        String sql = "Select * from BillStatistic where  statusCancel = 'Yes' ";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("billId"), result.getString("userId"),
                    result.getDate("transactionDate"), result.getString("paymentName"),
                    result.getString("statusCancel"), result.getString("quantity"),
                    result.getInt("quantity"), result.getString("mfgDate"), result.getString("expDate"),
                    result.getFloat("price"), result.getFloat("total")));
        }
        return oList;
    }

    // t???ng s??? l?????ng h??a ????n b??n ???????c theo ng??y
    public ObservableList<String> getNumbers(String startDate, String endDate) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select count(DISTINCT billId) as billQuantity from Bills where (transactiondate between ? and ? ) ";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("billQuantity"));
        }
        return oList;
    }

    // so luong hoa don bi huy
    public ObservableList<String> getNumberCancel(String startDate, String endDate) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select count(DISTINCT billId) as countCancel from Bills where statusCancel = 'YES' and (transactiondate between ? and ? )";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {

            oList.add(result.getString("countCancel"));
        }
        return oList;

    }

    // so luong hoa don ban theo ngay
    public ObservableList<String> getNumberDate(String startDate, String endDate) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select count(DISTINCT billId) as countDate from Bills where statusCancel = '' and (transactiondate between ? and ? )";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("countDate"));
        }
        return oList;
    }

    // tong tien bi huy
    public ObservableList<String> getTotalCancel(String startDate, String endDate) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select sum(total) as totalCancel from BillStatistic where (transactiondate between ? and ? ) and statusCancel = 'YES'";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("totalCancel"));
        }
        return oList;
    }
//tong doanh thu

    public ObservableList<String> getTotalDate(String startDate, String endDate) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select sum(total) as totalDate from BillStatistic where (transactiondate between ? and ? ) and statusCancel = ''";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getString("totalDate"));
        }
        return oList;
    }

    public ObservableList<BillStatistic> getBillCancelInf(String startDate, String endDate) throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
        String sql = "Select * from BillStatistic where  statusCancel = 'Yes' and (transactionDate between ? and ?)";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("billId"), result.getString("userId"),
                    result.getDate("transactionDate"), result.getString("paymentName"),
                    result.getString("statusCancel"), result.getString("quantity"),
                    result.getInt("quantity"), result.getString("mfgDate"), result.getString("expDate"),
                    result.getFloat("price"), result.getFloat("total")));
        }
        return oList;

    }

    public ObservableList<BillStatistic> getBillDateInfo(String startDate, String endDate) throws SQLException {
        ObservableList<BillStatistic> oList = FXCollections.observableArrayList();
        String sql = "Select * from BillStatistic where  statusCancel = '' and (transactionDate between ? and ?)";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, startDate);
        preStatement.setString(2, endDate);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new BillStatistic(result.getString("billId"), result.getString("userId"),
                    result.getDate("transactionDate"), result.getString("paymentName"),
                    result.getString("statusCancel"), result.getString("quantity"),
                    result.getInt("quantity"), result.getString("mfgDate"), result.getString("expDate"),
                    result.getFloat("price"), result.getFloat("total")));
        }
        return oList;
    }
    
    
     public ObservableList<XYChart.Data<String, Number>> getDateLineChart(String startDate, String endDate) throws SQLException {
        ObservableList<XYChart.Data<String, Number>> dList = FXCollections.observableArrayList();
        String sql ="select * from BillStatistic where (transactionDate between ? and ?) and statusCancel = ''" ;
        PreparedStatement preparedStatement = connect().prepareStatement(sql);
        preparedStatement.setString(1, startDate);
        preparedStatement.setString(2, endDate);
        preparedStatement.execute();
        ResultSet result = preparedStatement.getResultSet();
        while (result.next()) {
            dList.add(new XYChart.Data<String, Number>(result.getString("transactionDate"), result.getFloat("total")));
        }
         return dList;
        }
    
    // ---------------

    //  get list billId
    public ObservableList<String> getListBillId(String userId) throws SQLException {
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from bills "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(result.getString("billId"));
        }
        return oList;
    }

    //    delete bill by userId
    public boolean deleteBill(String userId) throws SQLException {
        String sql = "delete from bills "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }

}
