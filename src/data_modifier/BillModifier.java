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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author ADMIN
 */
public class BillModifier extends JDBCConnect {
    
    public ObservableList<VBills> getBillsInfo() throws SQLException{
    ObservableList<VBills> oList = FXCollections.observableArrayList();
    String sql = "Select * from VBills"; //viewsql
    PreparedStatement preStatement= connect().prepareStatement(sql);
    preStatement.execute();
    ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new VBills(result.getString("billId"), result.getString("userId"), result.getString("transactionDate"),
                    result.getString("paymentName"), result.getString("statusCancel"), result.getString("peoductId"), result.getInt("quantity"),
                    result.getString("mfgDate"), result.getString("expDate"), result.getFloat("price"))); //tencotsql
        }
        return oList;
}
    
}
