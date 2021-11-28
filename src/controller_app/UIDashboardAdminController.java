/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UIDashboardAdminController implements Initializable {

    public static String gUserId;
    public static String gFullName;
    public static String gPosition;

    String cssChange = "-fx-font-size: 14px; -fx-background-color: #d7ffb8; -fx-cursor: hand;";
    String cssDefault = "-fx-font-size: 14px; -fx-cursor: hand;";

    @FXML
    private BorderPane homePane;
    @FXML
    private VBox supMenuBox;
    @FXML
    private VBox menuBox;
    @FXML
    private HBox viewUserHB;
    @FXML
    private HBox createUserHB;
    @FXML
    private HBox updateUserHB;
    @FXML
    private HBox DeleteUserHB;
    @FXML
    private HBox viewSupHB;
    @FXML
    private HBox createSupHB;
    @FXML
    private HBox updateSupHB;
    @FXML
    private HBox deleteSupHB;
    @FXML
    private HBox editProfileHB;
    @FXML
    private HBox changePassHB;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private VBox childUserBox;
    @FXML
    private HBox childSettingBox;
    @FXML
    private VBox childSupplierBox;
    @FXML
    private VBox childPersonalInfoBox;
    @FXML
    private Label viewUserLB;
    @FXML
    private Label positionLB;
    @FXML
    private ComboBox<String> languageComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideSupMenu(false);
        hideChildUserBox(false);
        hideChildSupplierBox(false);
        hideChildPersonalInfoBox(false);
        hideChildSettingBox(false);
        
        setLanguageItem();
    }

    private void setLanguageItem() {
        ObservableList<String> sList = FXCollections.observableArrayList();
        sList.add("English");
        sList.add("Vietnamese");

        languageComboBox.setItems(sList);
        languageComboBox.setValue(sList.get(0));
    }

    private void setCSS(String cssDefault, String cssChange) {
//        viewUserHB.setStyle(cssDefault, cssChange);
//        createUserHB.setStyle(cssDefault, cssChange);
//        updateUserHB.setStyle(cssDefault, cssChange);
//        DeleteUserHB.setStyle(cssDefault, cssChange);
//        viewSupHB.setStyle(cssDefault, cssChange);
//        createSupHB.setStyle(cssDefault, cssChange);
//        updateSupHB.setStyle(cssDefault, cssChange);
//        deleteSupHB.setStyle(cssDefault, cssChange);
//        editProfileHB.setStyle(cssDefault, cssChange);
//        changePassHB.setStyle(cssDefault, cssChange);
    }

    private void hideChildUserBox(boolean value) {
        childUserBox.setVisible(value);
        childUserBox.managedProperty().bind(childUserBox.visibleProperty());
    }

    private void hideChildSupplierBox(boolean value) {
        childSupplierBox.setVisible(value);
        childSupplierBox.managedProperty().bind(childSupplierBox.visibleProperty());
    }

    private void hideChildPersonalInfoBox(boolean value) {
        childPersonalInfoBox.setVisible(value);
        childPersonalInfoBox.managedProperty().bind(childPersonalInfoBox.visibleProperty());
    }

    private void hideChildSettingBox(boolean value) {
        childSettingBox.setVisible(value);
        childSettingBox.managedProperty().bind(childSettingBox.visibleProperty());
    }

    public void setValueForVariableStatic(String userId, String fullName, String position) {
        gUserId = userId;
        userIdLabel.setText(userId);
        gFullName = fullName;
        fullNameLabel.setText(fullName);
        gPosition = position;
        positionLB.setText(position);

    }

    private void hideMenu(boolean value) {
        menuBox.setVisible(value);
        menuBox.managedProperty().bind(menuBox.visibleProperty());
    }

    private void hideSupMenu(boolean value) {
        supMenuBox.setVisible(value);
        supMenuBox.managedProperty().bind(supMenuBox.visibleProperty());
    }

    private void setCenterBox(String value) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_admin/" + value + ".fxml"));
        homePane.setCenter(root);
    }

    @FXML
    private void supMenuClicked(MouseEvent event) {
        hideMenu(true);
        hideSupMenu(false);
    }

    @FXML
    private void menuClicked(MouseEvent event) {
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void viewUserClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewUser");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void createUserClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateUser");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void updateUserClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateUser");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void deleteUserClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteUser");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void viewSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewSupplier");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void createSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateSupplier");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void updateSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateSupplier");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void deleteSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteSupplier");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void editProfileClicked(MouseEvent event) throws IOException {
        setCenterBox("EditProfile");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void changePasswordClicked(MouseEvent event) throws IOException {
        setCenterBox("ChangePassword");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void logoutClicked(MouseEvent event) throws IOException {
        gUserId = null;
        gFullName = null;
        gPosition = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view_admin/UILogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.show();
    }

    @FXML
    private void manageUserClicked(MouseEvent event) {
        hideChildUserBox(true);
        hideChildSupplierBox(false);
        hideChildPersonalInfoBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void manageSupplierClicked(MouseEvent event) {
        hideChildSupplierBox(true);
        hideChildUserBox(false);
        hideChildPersonalInfoBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void managePersonalInfoClicked(MouseEvent event) {
        hideChildPersonalInfoBox(true);
        hideChildUserBox(false);
        hideChildSupplierBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void settingClicked(MouseEvent event) {
        hideChildSettingBox(true);
        hideChildUserBox(false);
        hideChildSupplierBox(false);
        hideChildPersonalInfoBox(false);
    }

    @FXML
    private void positionClicked(MouseEvent event) {
    }

}
