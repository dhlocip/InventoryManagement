/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.Product;
import java.sql.*;
import javafx.collections.*;

/**
 *
 * @author sa
 */
public class ProductModifier extends JDBCConnect {
    
    // get list product info
    public ObservableList<Product> getListProduct() throws SQLException{
        ObservableList<Product> oList = FXCollections.observableArrayList();
        String sql = "select * from products";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(new Product(result.getString("productId"), 
                    result.getString("productName"), result.getString("categoryId"),
                    result.getDouble("price")));
        }
        return oList;
    }
    
    // get list product info after search
    public ObservableList<Product> getListProductAfterSearch(String idOrName) throws SQLException{
        ObservableList<Product> oList = FXCollections.observableArrayList();
        String sql = "select * from products "
                + "where productId like '%" + idOrName + "%' or productname like '%" + idOrName + "%'";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(new Product(result.getString("productId"), 
                    result.getString("productName"), result.getString("categoryId"),
                    result.getDouble("price")));
        }
        return oList;
    }
    
    // get list productId
    public ObservableList<String> getListProductId() throws SQLException{
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from products";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(result.getString("productId"));
        }
        return oList;
    }
    
//    create product
    public boolean createProduct(Product product) throws SQLException{
        String sql = "insert into products (productName, categoryId, price) "
                + "values (?,?,?)";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, product.getProductName());
        preS.setString(2, product.getCategoryId());
        preS.setDouble(3, product.getPrice());
        preS.execute();
        return true;
    }
    
//    update product 
    public boolean updateProduct(Product product) throws SQLException{
        String sql = "update products "
                + "set productName = ?, categoryId = ?, price = ? "
                + "where productId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, product.getProductName());
        preS.setString(2, product.getCategoryId());
        preS.setDouble(3, product.getPrice());
        preS.setString(4, product.getProductId());
        preS.executeUpdate();
        return true;
    }
    
//    delete product
    public boolean deleteProduct(String productId) throws SQLException{
        String sql = "delete from products "
                + "where productId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, productId);
        preS.execute();
        return true;
    }
    
    
//    public static void main(String[] args) throws SQLException {
//        System.out.println(new ProductModifier().deleteProduct("p0001"));
//    }
}
