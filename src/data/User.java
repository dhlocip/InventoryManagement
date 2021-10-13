/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author ADMIN
 */
public class User extends Person{
    private String birthday;
    private String hireDate;
    private String shift;
    private String userName;
    private String password;
    private String position;
    private String email;
    private String token;

    public User(String personId, String fullName, String address, String gender, String phone) {
        super(personId, fullName, address, gender, phone);
    }

    public User() {
    }
    
    public User(String birthday, String hireDate, String shift, String userName, String password, String position, String email, String token, String personId, String fullName, String address, String gender, String phone) {
        super(personId, fullName, address, gender, phone);
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.shift = shift;
        this.userName = userName;
        this.password = password;
        this.position = position;
        this.email = email;
        this.token = token;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    

    
    
    
}

