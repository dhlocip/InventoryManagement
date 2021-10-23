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
    
}
