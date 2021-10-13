/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.User;
import java.sql.*;

/**
 *
 * @author sa
 */
public class UserModifier extends JDBCConnect {
    
//    sign in -> userId, userName, position
    public User signIn(String userName, String password) throws SQLException{
        User user = new User();
        String sql = "select * from Users where userName = ? and password = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userName);
        preS.setString(2, password);
        preS.execute();
        
        ResultSet result = preS.getResultSet();
        while(result.next()){
            user.setPersonId(result.getString("userId"));
            user.setFullName(result.getString("fullName"));
            user.setPosition(result.getString("position"));
        }
        return user;
    }
    
//    public static void main(String[] args) throws SQLException {
//        User user = new UserModifier().signIn("admin", "rootroot");
//        System.out.println(user.getPosition());
//    }
    
}
