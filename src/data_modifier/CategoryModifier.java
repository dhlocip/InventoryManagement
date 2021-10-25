/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.Category;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class CategoryModifier extends JDBCConnect {
    
//    get list categoryId
    public ObservableList<String> getListCategoryId() throws SQLException{
        ObservableList<String> oList = FXCollections.observableArrayList();
        String sql = "select * from Categories";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(result.getString("categoryId"));
        }
        return oList;
    }
    
    //    get list category
    public ObservableList<Category> getListCategory() throws SQLException{
        ObservableList<Category> oList = FXCollections.observableArrayList();
        String sql = "select * from Categories";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(new Category(result.getString("categoryId"), result.getString("categoryName"), result.getString("description")));
        }
        return oList;
    }
    
    //    get list category after search
    public ObservableList<Category> getListCategoryAfterSearch(String idOrName) throws SQLException{
        ObservableList<Category> oList = FXCollections.observableArrayList();
        String sql = "select * from Categories "
                + "where categoryId like '%" + idOrName + "%' "
                + "or categoryName like '%" + idOrName + "%'";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            oList.add(new Category(result.getString("categoryId"), result.getString("categoryName"), result.getString("description")));
        }
        return oList;
    }
    
    // create category 
    public boolean createCategory(Category category) throws SQLException{
        String sql = "insert into Categories (caterogyName, description) "
                + "values (?,?)";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, category.getCategoryName());
        preS.setString(2, category.getDescription());
        preS.execute();
        return true;
    }
    
    // update category 
    public boolean updateCategory(Category category) throws SQLException{
        String sql = "update categories "
                + "set categoryName =?, description =? "
                + "where categoryId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, category.getCategoryName());
        preS.setString(2, category.getDescription());
        preS.setString(3, category.getCategoryId());
        preS.execute();
        return true;
    }
    
    // delete category
    public boolean deleteCategory(String categoryId) throws SQLException{
        String sql = "delete from categories where categoryId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, categoryId);
        preS.execute();
        return true;
    }
    
}
