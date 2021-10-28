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
    @FXML
    private HBox categoryBox;
    @FXML
    private VBox childCategoryBox;
    @FXML
    private HBox productBox;
    @FXML
    private VBox childProductBox;
    @FXML
    private HBox requestBox;
    @FXML
    private VBox childRequestBox;
    @FXML
    private HBox importBox;
    @FXML
    private VBox childImportBox;
    @FXML
    private HBox personalBox;
    @FXML
    private VBox childPersonalBox;
    @FXML
    private HBox settingBox;
    @FXML
    private HBox childSettingBox;
    @FXML
    private VBox childReportBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideSupMenu(false);

        hideChildCategoryBox(false);
        hideChildProductBox(false);
        hideChildRequestBox(false);
        hideChildReportBox(false);
        hideChildImportBox(false);
        hideChildPersonalBox(false);
        hideChildSettingBox(false);

    }

    public void setValueForVariableStatic(String userId, String fullName, String position) {
        gUserId = userId;
        userIdLabel.setText(gUserId);
        gFullName = fullName;
        fullNameLabel.setText(gFullName);
        gPosition = position;
        positionLabel.setText(gPosition);

    }

    private void hideChildCategoryBox(boolean value) {
        childCategoryBox.setVisible(value);
        childCategoryBox.managedProperty().bind(childCategoryBox.visibleProperty());
    }

    private void hideChildProductBox(boolean value) {
        childProductBox.setVisible(value);
        childProductBox.managedProperty().bind(childProductBox.visibleProperty());
    }

    private void hideChildRequestBox(boolean value) {
        childRequestBox.setVisible(value);
        childRequestBox.managedProperty().bind(childRequestBox.visibleProperty());
    }

    private void hideChildImportBox(boolean value) {
        childImportBox.setVisible(value);
        childImportBox.managedProperty().bind(childImportBox.visibleProperty());
    }

    private void hideChildPersonalBox(boolean value) {
        childPersonalBox.setVisible(value);
        childPersonalBox.managedProperty().bind(childPersonalBox.visibleProperty());
    }

    private void hideChildReportBox(boolean value) {
        childReportBox.setVisible(value);
        childReportBox.managedProperty().bind(childReportBox.visibleProperty());
    }

    private void hideChildSettingBox(boolean value) {
        childSettingBox.setVisible(value);
        childSettingBox.managedProperty().bind(childSettingBox.visibleProperty());
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

    private void setCenterBoxFromViewSaleManager(String value) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_sale_manager/" + value + ".fxml"));
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
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void createProductClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateProduct");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void updateProductClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateProduct");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void deleteProductClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteProduct");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void viewImportClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewImport");
        hideSupMenu(true);
        hideMenu(false);

    }

    @FXML
    private void createImportClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateImport");
        hideSupMenu(true);
        hideMenu(false);

    }

    @FXML
    private void updateImportClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateImport");
        hideSupMenu(true);
        hideMenu(false);

    }

    @FXML
    private void deleteImportClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteImport");
        hideSupMenu(true);
        hideMenu(false);

    }

    @FXML
    private void editProfileClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewAdmin("EditProfile");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void changePasswordClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewAdmin("ChangePassword");
        hideSupMenu(true);
        hideMenu(false);

    }

    @FXML
    private void inventoryByDateClicked(MouseEvent event) throws IOException {
        setCenterBox("InventoryByDate");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void inventoryByCategoryClicked(MouseEvent event) throws IOException {
        setCenterBox("InventoryByCategory");
        hideSupMenu(true);
        hideMenu(false);

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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.show();
    }

    @FXML
    private void viewRequestClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewSaleManager("Request");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void viewNewRequestClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewSaleManager("NewRequest");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void viewCategoryClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewCategory");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void createCategoryClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateCategory");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void updateCategoryClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateCategory");
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void deleteCategoryClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteCategory");
        hideSupMenu(true);
        hideMenu(false);
        
    }

    @FXML
    private void manageCategoryClicked(MouseEvent event) {
        hideChildCategoryBox(true);
        hideChildProductBox(false);
        hideChildRequestBox(false);
        hideChildReportBox(false);
        hideChildImportBox(false);
        hideChildPersonalBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void manageProductClicked(MouseEvent event) {
        hideChildProductBox(true);
        hideChildCategoryBox(false);
        hideChildRequestBox(false);
        hideChildReportBox(false);
        hideChildImportBox(false);
        hideChildPersonalBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void manageStockClicked(MouseEvent event) {
        hideChildImportBox(true);
        hideChildCategoryBox(false);
        hideChildProductBox(false);
        hideChildRequestBox(false);
        hideChildReportBox(false);
        hideChildPersonalBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void managePersonalClicked(MouseEvent event) {
        hideChildPersonalBox(true);
        hideChildCategoryBox(false);
        hideChildProductBox(false);
        hideChildRequestBox(false);
        hideChildReportBox(false);
        hideChildImportBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void reportClicked(MouseEvent event) {
        hideChildReportBox(true);
        hideChildCategoryBox(false);
        hideChildProductBox(false);
        hideChildRequestBox(false);
        hideChildImportBox(false);
        hideChildPersonalBox(false);
        hideChildSettingBox(false);
    }

    @FXML
    private void settingClicked(MouseEvent event) {
        hideChildSettingBox(true);
        hideChildCategoryBox(false);
        hideChildProductBox(false);
        hideChildRequestBox(false);
        hideChildReportBox(false);
        hideChildImportBox(false);
        hideChildPersonalBox(false);
    }

    @FXML
    private void requestClicked(MouseEvent event) {
        hideChildRequestBox(true);
        hideChildCategoryBox(false);
        hideChildProductBox(false);
        hideChildReportBox(false);
        hideChildImportBox(false);
        hideChildPersonalBox(false);
        hideChildSettingBox(false);
    }

}
