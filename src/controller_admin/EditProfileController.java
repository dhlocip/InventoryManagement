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
import javafx.scene.control.TextField;
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
    }

}
