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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class EditProfileController implements Initializable {

    String lUserId;

    @FXML
    private TextField userIdTF;
    @FXML
    private TextField fullNameTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField shiffTF;
    @FXML
    private ComboBox<String> positionComboBox;
    @FXML
    private TextField emailTF;
    @FXML
    private DatePicker birthDayDatePicker;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private Label errorFullName;
    @FXML
    private Label errorBirthDay;
    @FXML
    private Label errorHireDate;
    @FXML
    private Label errorAddress;
    @FXML
    private Label errorPhone;
    @FXML
    private Label errorGender;
    @FXML
    private Label errorShiff;
    @FXML
    private Label errorEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        setGender();
        setPosition();

        try {
            getPersonalInfo();

        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hideErrorOfFullName(false);
        hideErrorOfBirthday(false);
        hideErrorOfHireDate(false);
        hideErrorOfAddress(false);
        hideErrorOfPhone(false);
        hideErrorOfGender(false);
        hideErrorOfShiff(false);
        hideErrorOfEmail(false);

    }
    
    private void hideErrorOfFullName(boolean value){
        errorFullName.setVisible(value);
        errorFullName.managedProperty().bind(errorFullName.visibleProperty());
    }
    
    private void hideErrorOfBirthday(boolean value){
        errorBirthDay.setVisible(value);
        errorBirthDay.managedProperty().bind(errorBirthDay.visibleProperty());
    }
    
    private void hideErrorOfHireDate(boolean value){
        errorHireDate.setVisible(value);
        errorHireDate.managedProperty().bind(errorHireDate.visibleProperty());
    }
    
    private void hideErrorOfAddress(boolean value){
        errorAddress.setVisible(value);
        errorAddress.managedProperty().bind(errorAddress.visibleProperty());
    }
    
    private void hideErrorOfPhone(boolean value){
        errorPhone.setVisible(value);
        errorPhone.managedProperty().bind(errorPhone.visibleProperty());
    }
    
    private void hideErrorOfGender(boolean value){
        errorGender.setVisible(value);
        errorGender.managedProperty().bind(errorGender.visibleProperty());
    }

    private void hideErrorOfShiff(boolean value){
        errorShiff.setVisible(value);
        errorShiff.managedProperty().bind(errorShiff.visibleProperty());
    }
    
    private void hideErrorOfEmail(boolean value){
        errorEmail.setVisible(value);
        errorEmail.managedProperty().bind(errorEmail.visibleProperty());
    }
    
    private void getPersonalInfo() throws SQLException {
        if (UIDashboardAdminController.gPosition != null
                && UIDashboardInventoryManagerController.gPosition == null
                && UIDashboardSaleManagerController.gPosition == null) {
            lUserId = UIDashboardAdminController.gUserId;
            User user = new User();
            user = new UserModifier().getUser(lUserId);
            userIdTF.setText(user.getPersonId());
            fullNameTF.setText(user.getFullName());
            
            String formatBirthDay = user.getBirthday().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate localBirthDay = LocalDate.parse(formatBirthDay);
            birthDayDatePicker.setValue(localBirthDay);
            
            String formatHireDate = user.getHireDate().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate hireDate = LocalDate.parse(formatHireDate);
            hireDatePicker.setValue(hireDate);
            
            addressTF.setText(user.getAddress());
            phoneTF.setText(user.getPhone());
            genderComboBox.setValue(user.getGender());
            shiffTF.setText(user.getShift());
            positionComboBox.setValue(user.getPosition());
            emailTF.setText(user.getEmail());
        } else if (UIDashboardInventoryManagerController.gPosition != null
                && UIDashboardAdminController.gPosition == null
                && UIDashboardSaleManagerController.gPosition == null) {
            lUserId = UIDashboardInventoryManagerController.gUserId;
            User user = new User();
            user = new UserModifier().getUser(lUserId);
            userIdTF.setText(user.getPersonId());
            fullNameTF.setText(user.getFullName());
            
            String formatBirthDay = user.getBirthday().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate localBirthDay = LocalDate.parse(formatBirthDay);
            birthDayDatePicker.setValue(localBirthDay);
            
            String formatHireDate = user.getHireDate().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate hireDate = LocalDate.parse(formatHireDate);
            hireDatePicker.setValue(hireDate);
            
            addressTF.setText(user.getAddress());
            phoneTF.setText(user.getPhone());
            genderComboBox.setValue(user.getGender());
            shiffTF.setText(user.getShift());
            positionComboBox.setValue(user.getPosition());
            emailTF.setText(user.getEmail());
        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
            User user = new User();
            user = new UserModifier().getUser(lUserId);
            userIdTF.setText(user.getPersonId());
            fullNameTF.setText(user.getFullName());
            
            String formatBirthDay = user.getBirthday().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate localBirthDay = LocalDate.parse(formatBirthDay);
            birthDayDatePicker.setValue(localBirthDay);
            
            String formatHireDate = user.getHireDate().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate hireDate = LocalDate.parse(formatHireDate);
            hireDatePicker.setValue(hireDate);
            
            addressTF.setText(user.getAddress());
            phoneTF.setText(user.getPhone());
            genderComboBox.setValue(user.getGender());
            shiffTF.setText(user.getShift());
            positionComboBox.setValue(user.getPosition());
            emailTF.setText(user.getEmail());
        }
    }

    private void setGender() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.addAll("Male", "Female");
        genderComboBox.setItems(oList);
        
        genderComboBox.setOnAction((t) -> {
            genderComboBox.getValue();
        });
    }

    private void setPosition() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.addAll("Admin", "Inventory Manager", "Sale Manager", "Sale Person");
        positionComboBox.setItems(oList);
        
        positionComboBox.setOnAction((t) -> {
            positionComboBox.getValue();
        });
    }

    @FXML
    private void updateProfileClicked(MouseEvent event) throws SQLException {
//        User user = new User();
//        user.setPersonId(lUserId);
//        user.setFullName(fullNameTF.getText());
        System.out.println(genderComboBox.getValue());
        System.out.println(birthDayDatePicker.getValue());
    }
    
    private boolean isFullNameRight(){
        String tmp = fullNameTF.getText();
        return tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{3,50}");
    }

    @FXML
    private void fullNameReleased(KeyEvent event) {
        if(isFullNameRight()){
            hideErrorOfFullName(false);
        }else{
            hideErrorOfFullName(true);
            errorFullName.setText("\"" + fullNameTF.getText() + "\" is invalid.");
        }
    }

    @FXML
    private void addressReleased(KeyEvent event) {
    }

    @FXML
    private void phoneReleased(KeyEvent event) {
    }

    @FXML
    private void shiffReleased(KeyEvent event) {
    }

    @FXML
    private void emailReleased(KeyEvent event) {
    }

}
