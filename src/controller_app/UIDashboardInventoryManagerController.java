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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UIDashboardInventoryManagerController implements Initializable {

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

    private void hideMenu(boolean value) {
        menuBox.setVisible(value);
        menuBox.managedProperty().bind(menuBox.visibleProperty());
    }

    private void hideSupMenu(boolean value) {
        supMenuBox.setVisible(value);
        supMenuBox.managedProperty().bind(supMenuBox.visibleProperty());
    }

    private void setCenterBox(String value) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_inventory_manager/" + value + ".fxml"));
        homePane.setCenter(root);
    }

    private void setCenterBoxFromViewAdmin(String value) throws IOException {
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
    private void viewProductClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewProduct");

    }

    @FXML
    private void createProductClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateProduct");

    }

    @FXML
    private void updateProductClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateProduct");

    }

    @FXML
    private void deleteProductClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteProduct");

    }

    @FXML
    private void viewImportClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewImport");

    }

    @FXML
    private void createImportClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateImport");

    }

    @FXML
    private void updateImportClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateImport");

    }

    @FXML
    private void deleteImportClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteImport");

    }

    @FXML
    private void editProfileClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewAdmin("EditProfile");
    }

    @FXML
    private void changePasswordClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewAdmin("ChangePassword");

    }

    @FXML
    private void inventoryByDateClicked(MouseEvent event) throws IOException {
        setCenterBox("InventoryByDate");
    }

    @FXML
    private void inventoryByCategoryClicked(MouseEvent event) throws IOException {
        setCenterBox("InventoryByCategory");

    }

    @FXML
    private void languageClicked(MouseEvent event) {
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
