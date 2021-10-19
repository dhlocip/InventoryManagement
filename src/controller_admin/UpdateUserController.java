/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import data.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UpdateUserController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TextField userIdTF;
    @FXML
    private Label errorUserId;
    @FXML
    private TextField fullNameTF;
    @FXML
    private Label errorFullName;
    @FXML
    private Label errorBirthday;
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
    private TableColumn<User, String> tokenCol;
    @FXML
    private DatePicker birthdayDatePicker;
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
    private void updateUserClicked(MouseEvent event) {
    }

    @FXML
    private void userTableViewClicked(MouseEvent event) {
    }

    @FXML
    private void fullNameReleased(KeyEvent event) {
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
    private void userNameReleased(KeyEvent event) {
    }

    @FXML
    private void passwordReleased(KeyEvent event) {
    }

    @FXML
    private void emailReleased(KeyEvent event) {
    }
    
}
