/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import controller_app.UIDashboardAdminController;
import controller_app.UIDashboardInventoryManagerController;
import controller_app.UIDashboardSaleManagerController;
import data.User;
import data_modifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class CreateUserController implements Initializable {
    
    String lUserId;
    
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> userIdCol;
    @FXML
    private TableColumn<User, String> fullNameCol;
    @FXML
    private TableColumn<User, String> birthdayCol;
    @FXML
    private TableColumn<User, String> hireDateCol;
    @FXML
    private TableColumn<User, String> addressCol;
    @FXML
    private TableColumn<User, String> phoneCol;
    @FXML
    private TableColumn<User, String> genderCol;
    @FXML
    private TableColumn<User, String> shiffCol;
    @FXML
    private TableColumn<User, String> userNameCol;
    @FXML
    private TableColumn<User, String> passwordCol;
    @FXML
    private TableColumn<User, String> positionCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TextField userIdTF;
    @FXML
    private Label errorUserId;
    @FXML
    private TextField fullNameTF;
    @FXML
    private Label errorFullName;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private Label errorBirthday;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private Label errorHireDate;
    @FXML
    private TextField addressTF;
    @FXML
    private Label errorAddress;
    @FXML
    private TextField phoneTF;
    @FXML
    private Label errorPhone;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private Label errorGender;
    @FXML
    private TextField shiffTF;
    @FXML
    private Label errorShiff;
    @FXML
    private TextField userNameTF;
    @FXML
    private Label errorUserName;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label errorPassword;
    @FXML
    private ComboBox<String> positionComboBox;
    @FXML
    private Label errorPosition;
    @FXML
    private TextField emailTF;
    @FXML
    private Label errorEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //        get userId
        if (UIDashboardAdminController.gPosition != null
                && UIDashboardInventoryManagerController.gPosition == null
                && UIDashboardSaleManagerController.gPosition == null) {
            lUserId = UIDashboardAdminController.gUserId;
            
        } else if (UIDashboardAdminController.gPosition == null
                && UIDashboardInventoryManagerController.gPosition != null
                && UIDashboardSaleManagerController.gPosition == null) {
            
            lUserId = UIDashboardInventoryManagerController.gUserId;
        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
        }
        
        setGender();
        setPosition();
        
        hideErrorOfUserId(false);
        hideErrorOfFullName(false);
        hideErrorOfBirthday(false);
        hideErrorOfHireDate(false);
        hideErrorOfAddress(false);
        hideErrorOfPhone(false);
        hideErrorOfGender(false);
        hideErrorOfShiff(false);
        hideErrorOfUserName(false);
        hideErrorOfPassword(false);
        hideErrorOfPosition(false);
        hideErrorOfEmail(false);
        
        try {
            getListUserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void hideErrorOfUserId(boolean value) {
        errorUserId.setVisible(value);
        errorUserId.managedProperty().bind(errorUserId.visibleProperty());
    }
    
    private void hideErrorOfFullName(boolean value) {
        errorFullName.setVisible(value);
        errorFullName.managedProperty().bind(errorFullName.visibleProperty());
    }
    
    private void hideErrorOfBirthday(boolean value) {
        errorBirthday.setVisible(value);
        errorBirthday.managedProperty().bind(errorBirthday.visibleProperty());
    }
    
    private void hideErrorOfHireDate(boolean value) {
        errorHireDate.setVisible(value);
        errorHireDate.managedProperty().bind(errorHireDate.visibleProperty());
    }
    
    private void hideErrorOfAddress(boolean value) {
        errorAddress.setVisible(value);
        errorAddress.managedProperty().bind(errorAddress.visibleProperty());
    }
    
    private void hideErrorOfPhone(boolean value) {
        errorPhone.setVisible(value);
        errorPhone.managedProperty().bind(errorPhone.visibleProperty());
    }
    
    private void hideErrorOfGender(boolean value) {
        errorGender.setVisible(value);
        errorGender.managedProperty().bind(errorGender.visibleProperty());
    }
    
    private void hideErrorOfShiff(boolean value) {
        errorShiff.setVisible(value);
        errorShiff.managedProperty().bind(errorShiff.visibleProperty());
    }
    
    private void hideErrorOfUserName(boolean value) {
        errorUserName.setVisible(value);
        errorUserName.managedProperty().bind(errorUserName.visibleProperty());
    }
    
    private void hideErrorOfPassword(boolean value) {
        errorPassword.setVisible(value);
        errorPassword.managedProperty().bind(errorPassword.visibleProperty());
    }
    
    private void hideErrorOfPosition(boolean value) {
        errorPosition.setVisible(value);
        errorPosition.managedProperty().bind(errorPosition.visibleProperty());
    }
    
    private void hideErrorOfEmail(boolean value) {
        errorEmail.setVisible(value);
        errorEmail.managedProperty().bind(errorEmail.visibleProperty());
    }
    
    private void getListUserInfo() throws SQLException {
        ObservableList<User> oList = new UserModifier().getListUser();
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("personId"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        shiffCol.setCellValueFactory(new PropertyValueFactory<>("shiff"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTableView.setItems(oList);
    }
    
    private void setGender() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.addAll("Male", "Female");
        genderComboBox.setItems(oList);
        genderComboBox.setValue("Male");
        
        genderComboBox.setOnAction((t) -> {
            genderComboBox.getValue();
        });
    }
    
    private void setPosition() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.addAll("Admin", "Inventory Manager", "Sale Manager", "Sale Person");
        positionComboBox.setItems(oList);
        positionComboBox.setValue("Sale Person");
        
        positionComboBox.setOnAction((t) -> {
            positionComboBox.getValue();
        });
    }
    
    @FXML
    private void createUserClicked(MouseEvent event) {
        User user = new User();
        if (isFullNameRight() && birthdayDatePicker.getValue() != null && hireDatePicker.getValue() != null
                && isAddressRight() && isPhoneRight() && isShiffRight() && isUserNameRight() && isPasswordRight()
                && isEmailRight()) {
            user.setFullName(fullNameTF.getText());
            
            LocalDate myBirthday = birthdayDatePicker.getValue();
            user.setBirthday(myBirthday.toString());
            
            LocalDate myHireDate = hireDatePicker.getValue();
            user.setHireDate(myHireDate.toString());
            
            user.setAddress(addressTF.getText());
            user.setPhone(phoneTF.getText());
            user.setGender(genderComboBox.getValue());
            user.setShiff(shiffTF.getText());
            user.setUserName(userNameTF.getText());
            user.setPassword(passwordPF.getText());
            user.setPosition(positionComboBox.getValue());
            user.setEmail(emailTF.getText());
            
            System.out.println(user.getFullName());
            System.out.println(user.getBirthday());
            System.out.println(user.getHireDate());
            System.out.println(user.getAddress());
            System.out.println(user.getPhone());
            System.out.println(user.getGender());
            System.out.println(user.getShiff());
            System.out.println(user.getUserName());
            System.out.println(user.getPassword());
            System.out.println(user.getPosition());
            System.out.println(user.getEmail());
        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Notification");
//            alert.setHeaderText("Error");
//            alert.setContentText("Text fields are not empty.");
//            alert.showAndWait();
            checkFullName();
            if (birthdayDatePicker.getValue() == null) {
                hideErrorOfBirthday(true);
                errorBirthday.setText("\"birthday\" is not null.");
            } else {
                hideErrorOfBirthday(false);
            }
            
            if (hireDatePicker.getValue() == null) {
                hideErrorOfHireDate(true);
                errorHireDate.setText("\"hire date\" is not null.");
            } else {
                hideErrorOfHireDate(false);
            }
            checkAddress();
            checkPhone();
            checkShiff();
            checkUserName();
            checkPassword();
            checkEmail();
        }
    }
    
    private boolean isFullNameRight() {
        String tmp = fullNameTF.getText();
        return tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{3,50}");
    }
    
    private void checkFullName() {
        if (isFullNameRight()) {
            hideErrorOfFullName(false);
        } else {
            hideErrorOfFullName(true);
            errorFullName.setText("\"" + fullNameTF.getText() + "\" is invalid.");
        }
    }
    
    @FXML
    private void fullNameReleased(KeyEvent event) {
        checkFullName();
    }
    
    private boolean isAddressRight() {
        String tmp = addressTF.getText();
        return tmp.matches("^[\\w]{1}[\\w\\s,.]{5,100}");
    }
    
    private void checkAddress() {
        if (isAddressRight()) {
            hideErrorOfAddress(false);
        } else {
            hideErrorOfAddress(true);
            errorAddress.setText("\"" + addressTF.getText() + "\" is invalid.");
        }
    }
    
    @FXML
    private void addressReleased(KeyEvent event) {
        checkAddress();
    }
    
    private boolean isPhoneRight() {
        String tmp = phoneTF.getText();
        return tmp.matches("^[0]{1}[\\d]{9,10}");
    }
    
    private void checkPhone() {
        if (isPhoneRight()) {
            hideErrorOfPhone(false);
        } else {
            hideErrorOfPhone(true);
            errorPhone.setText("\"" + phoneTF.getText() + "\" is invalid.");
        }
    }
    
    @FXML
    private void phoneReleased(KeyEvent event) {
        checkPhone();
    }
    
    private boolean isShiffRight() {
        String tmp = shiffTF.getText();
        return tmp.matches("^[\\w]{1,5}");
    }
    
    private void checkShiff() {
        if (isShiffRight()) {
            hideErrorOfShiff(false);
        } else {
            hideErrorOfShiff(true);
            errorShiff.setText("\"" + shiffTF.getText() + "\" is invalid.");
        }
    }
    
    @FXML
    private void shiffReleased(KeyEvent event) {
        checkShiff();
    }
    
    private boolean isUserNameRight() {
        String tmp = userNameTF.getText();
        return tmp.matches("^[a-zA-Z]+[_.]?[\\w]*") && tmp.length() >= 5;
    }
    
    private void checkUserName() {
        if (isUserNameRight()) {
            hideErrorOfUserName(false);
        } else {
            hideErrorOfUserName(true);
            errorUserName.setText("\"" + userNameTF.getText() + "\" is invalid.");
        }
    }
    
    @FXML
    private void userNameReleased(KeyEvent event) {
        checkUserName();
    }
    
    private boolean isPasswordRight() {
        String tmp = passwordPF.getText();
        return tmp.matches("^[\\w_.!@#$%^*]+") && tmp.length() >= 8;
    }
    
    private void checkPassword() {
        if (isPasswordRight()) {
            hideErrorOfPassword(false);
        } else {
            hideErrorOfPassword(true);
            errorPassword.setText("\"password\" is invalid.");
        }
    }
    
    @FXML
    private void passwordReleased(KeyEvent event) {
        checkPassword();
    }
    
    private boolean isEmailRight() {
        String tmp = emailTF.getText();
        return tmp.matches("^[^\\W\\d]{1}[\\w]+[.]?[\\w]+[@]{1}[^\\W\\d]{4,7}[.]{1}[^\\W\\d]{3}[.]{0,1}[^\\W\\d]{0,3}[.]{0,1}[^\\W\\d]{0,2}");
    }
    
    private void checkEmail() {
        if (isEmailRight()) {
            hideErrorOfEmail(false);
        } else {
            hideErrorOfEmail(true);
            errorEmail.setText("\"" + emailTF.getText() + "\" is invalid.");
        }
    }
    
    @FXML
    private void emailReleased(KeyEvent event) {
        checkEmail();
    }

    @FXML
    private void birthdayAction(ActionEvent event) {
        hideErrorOfBirthday(false);
    }

    @FXML
    private void hireDateAction(ActionEvent event) {
        hideErrorOfHireDate(false);
    }
    
}
