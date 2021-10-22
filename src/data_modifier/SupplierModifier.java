/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.Suppliers;
import static data_modifier.JDBCConnect.connect;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class SupplierModifier extends JDBCConnect {

    // get list supplier info
    public ObservableList<Suppliers> getListSupplierInfo() throws SQLException {
        ObservableList<Suppliers> oList = FXCollections.observableArrayList();
        String sql = "select * from suppliers";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new Suppliers(result.getString("supplierId"), result.getString("companyName"),
                    result.getString("address"), result.getString("phone"), result.getString("homePage"),
                    result.getString("personRepresentative")));
        }
        return oList;
    }
    
    // get list supplier info after search
    public ObservableList<Suppliers> getListSupplierInfoAfterSearch(String value) throws SQLException {
        ObservableList<Suppliers> oList = FXCollections.observableArrayList();
        String sql = "select * from suppliers where supplierId like '%" + value + "%' or "
                + "companyName like '%" + value + "%'";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new Suppliers(result.getString("supplierId"), result.getString("companyName"),
                    result.getString("address"), result.getString("phone"), result.getString("homePage"),
                    result.getString("personRepresentative")));
        }
        return oList;
    }
    
    // get supplier info with supplierID
    public Suppliers getSupplier(String supplierId) throws SQLException{
        Suppliers supplier = new Suppliers();
        String sql = "select * from suppliers where supplierId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, supplierId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            supplier.setSupplierId(result.getString("supplierId"));
            supplier.setCompanyName(result.getString("companyName"));
            supplier.setAddress(result.getString("address"));
            supplier.setPhone(result.getString("phone"));
            supplier.setHomePage(result.getString("homePage"));
            supplier.setPersonRepresentative(result.getString("personRepresentative"));
        }
        return supplier;
    }
    
    // create supplier
    public boolean createSupplier(Suppliers supplier) throws SQLException{
        String sql = "insert into suppliers "
                + "(companyName, address, phone, homePage, personRepresentative) "
                + "values "
                + "(?,?,?,?,?)";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, supplier.getCompanyName());
        preS.setString(2, supplier.getAddress());
        preS.setString(3, supplier.getPhone());
        preS.setString(4, supplier.getHomePage());
        preS.setString(5, supplier.getPersonRepresentative());
        preS.execute();
        return true;
    }
    
    // update supplier
    public boolean updateSupplier(Suppliers supplier) throws SQLException {
        String sql = "update suppliers "
                + "set companyName = ?, address = ?, phone = ?, "
                + "homePage = ?, personRepresentative = ? "
                + "where supplierId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, supplier.getCompanyName());
        preS.setString(2, supplier.getAddress());
        preS.setString(3, supplier.getPhone());
        preS.setString(4, supplier.getHomePage());
        preS.setString(5, supplier.getPersonRepresentative());
        preS.setString(6, supplier.getSupplierId());
        preS.executeUpdate();
        return true;
    }
    
    // delete supplier
    public boolean deleteSupplier(String supplierId) throws SQLException{
        String sql = "delete from suppliers "
                + "where supplierId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, supplierId);
        preS.execute();
        return true;
    }

}
