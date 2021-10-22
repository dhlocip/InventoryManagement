/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_modifier;

import data.User;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class UserModifier extends JDBCConnect {

//    sign in -> userId, userName, position
    public User signIn(String userName, String password) throws SQLException {
        User user = new User();
        String sql = "select * from Users where userName = ? and password = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userName);
        preS.setString(2, password);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            user.setPersonId(result.getString("userId"));
            user.setFullName(result.getString("fullName"));
            user.setPosition(result.getString("position"));
        }
        return user;
    }

//    change password
    public boolean changePassword(String userId, String password) throws SQLException{
        String sql = "update Users "
                + "set password = ? "
                + "where userId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, password);
        preS.setString(2, userId);
        preS.executeUpdate();
        return true;
    }
    
//    find user with userID or fullName
    public ObservableList<User> findUser(String idOrFullName) throws SQLException {
        ObservableList<User> oList = FXCollections.observableArrayList();
        String sql = "select * from Users where userId like '%" + idOrFullName + "%' or "
                + "fullName like '%" + idOrFullName + "%'";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new User(result.getString("userId"), result.getString("fullName"), 
                    result.getString("birthday"), result.getString("hireDate"), result.getString("address"), 
                    result.getString("phone"), result.getString("gender"), result.getString("shiff"), 
                    result.getString("userName"), result.getString("password"), result.getString("position"), 
                    result.getString("email"), result.getString("token")));
        }
        return oList;
    }
    
//    check user name is exists
    public boolean userNameIsExists(String userName) throws SQLException{
        String sql = "select * from users where userName = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userName);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            return true;
        }
        return false;
    }
    
//    get user info with userID
    public User getUser(String userId) throws SQLException {
        User user = new User();
        String sql = "select * from Users where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            user.setPersonId(result.getString("userId"));
            user.setFullName(result.getString("fullName"));
            user.setBirthday(result.getString("birthDay"));
            user.setHireDate(result.getString("hireDate"));
            user.setAddress(result.getString("address"));
            user.setPhone(result.getString("phone"));
            user.setGender(result.getString("gender"));
            user.setShiff(result.getString("shiff"));
            user.setUserName(result.getString("userName"));
            user.setPassword(result.getString("password"));
            user.setPosition(result.getString("position"));
            user.setEmail(result.getString("email"));
        }
        return user;
    }
    
    //    get list user info with userID
    public ObservableList<User> getListUser() throws SQLException {
        ObservableList<User> oList = FXCollections.observableArrayList();
        String sql = "select * from Users";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            oList.add(new User(result.getString("userId"), result.getString("fullName"), 
                    result.getString("birthday"), result.getString("hireDate"), result.getString("address"), 
                    result.getString("phone"), result.getString("gender"), result.getString("shiff"), 
                    result.getString("userName"), result.getString("password"), result.getString("position"), 
                    result.getString("email"), result.getString("token")));
        }
        return oList;
    }

//    update user info with userId, personal user
    public boolean updateUser(User user) throws SQLException {
        String sql = "update Users "
                + "set fullName = ?, birthDay = ?, hireDate = ?, address = ?, "
                + "phone = ?, gender = ?, shiff = ?, email = ? "
                + "where userId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, user.getFullName());
        preS.setString(2, user.getBirthday());
        preS.setString(3, user.getHireDate());
        preS.setString(4, user.getAddress());
        preS.setString(5, user.getPhone());
        preS.setString(6, user.getGender());
        preS.setString(7, user.getShiff());
        preS.setString(8, user.getEmail());
        preS.setString(9, user.getPersonId());
        preS.executeUpdate();
        return true;
    }

    //    craete user info with personal user
    public boolean createUser(User user) throws SQLException {
        String sql = "insert into Users "
                + "(fullName, birthday, hireDate, address, phone, gender, shiff, userName, password, position, email) "
                + "values "
                + "(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, user.getFullName());
        preS.setString(2, user.getBirthday());
        preS.setString(3, user.getHireDate());
        preS.setString(4, user.getAddress());
        preS.setString(5, user.getPhone());
        preS.setString(6, user.getGender());
        preS.setString(7, user.getShiff());
        preS.setString(8, user.getUserName());
        preS.setString(9, user.getPassword());
        preS.setString(10, user.getPosition());
        preS.setString(11, user.getEmail());
        preS.execute();
        return true;
    }
    
//    delete user
    public boolean deleteUser(String userId) throws SQLException{
        String sql = "delete from users "
                + "where userId =?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, userId);
        preS.execute();
        return true;
    }

}
