/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private HBox languageHB;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label fullNameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideSupMenu(false);
        
        
    }
    
    public void setValueForVariableStatic(String userId, String fullName, String position){
        gUserId = userId;
        userIdLabel.setText(userId);
        gFullName = fullName;
        fullNameLabel.setText(fullName);
        gPosition = position;
        positionLabel.setText(position);
        
    }

    private void hideMenu(boolean value){
        menuBox.setVisible(value);
        menuBox.managedProperty().bind(menuBox.visibleProperty());
    }
    
    private void hideSupMenu(boolean value){
        supMenuBox.setVisible(value);
        supMenuBox.managedProperty().bind(supMenuBox.visibleProperty());
    }
    
    private void setCenterBox(String value) throws IOException{
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
    }

    @FXML
    private void createUserClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateUser");
    }

    @FXML
    private void updateUserClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateUser");
    }

    @FXML
    private void deleteUserClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteUser");
    }

    @FXML
    private void viewSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewSupplier");
    }

    @FXML
    private void createSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateSupplier");
    }

    @FXML
    private void updateSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateSupplier");
    }

    @FXML
    private void deleteSupplierClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteSupplier");
    }

    @FXML
    private void editProfileClicked(MouseEvent event) throws IOException {
        setCenterBox("EditProfile");
    }
    
    @FXML
    private void changePasswordClicked(MouseEvent event) throws IOException {
        setCenterBox("ChangePassword");
    }

    @FXML
    private void logoutClicked(MouseEvent event) throws IOException {
        gUserId = null;
        gFullName = null;
        gPosition = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view_admin/UILogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.show();
    }

    
    
}
