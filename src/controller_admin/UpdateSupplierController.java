/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import data.Category;
import data.Suppliers;
import data_modifier.CategoryModifier;
import data_modifier.SupplierModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
public class UpdateSupplierController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TextField companyNameTF;
    @FXML
    private Label errorCompanyName;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private Label errorPhone;
    @FXML
    private TableColumn<Suppliers, String> supplierIdCol;
    @FXML
    private TableColumn<Suppliers, String> companyNameCol;
    @FXML
    private TableColumn<Suppliers, String> addressCol;
    @FXML
    private TableColumn<Suppliers, String> phoneCol;
    @FXML
    private TableColumn<Suppliers, String> homePageCol;
    @FXML
    private TableColumn<Suppliers, String> personCol;
    @FXML
    private Label errorAddress;
    @FXML
    private TableView<Suppliers> supplierTableView;
    @FXML
    private TextField homePageTF;
    @FXML
    private TextField personTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getListSupplier();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

        hideErrorOfAddress(false);
        hideErrorOfCompanyName(false);
        hideErrorOfPhone(false);
    }

    private void hideErrorOfAddress(boolean value) {
        errorAddress.setVisible(value);
        errorAddress.managedProperty().bind(errorAddress.visibleProperty());
    }

    private void hideErrorOfCompanyName(boolean value) {
        errorCompanyName.setVisible(value);
        errorCompanyName.managedProperty().bind(errorCompanyName.visibleProperty());
    }

    private void hideErrorOfPhone(boolean value) {
        errorPhone.setVisible(value);
        errorPhone.managedProperty().bind(errorPhone.visibleProperty());
    }

    private void getListSupplier() throws SQLException {
        ObservableList<Suppliers> oList = new SupplierModifier().getListSupplierInfo();

        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        homePageCol.setCellValueFactory(new PropertyValueFactory<>("homePage"));
        personCol.setCellValueFactory(new PropertyValueFactory<>("personRepresentative"));

        supplierTableView.setItems(oList);
    }

    private void getListSupplierAfterSearch(String idOrCompanyName) throws SQLException {
        ObservableList<Suppliers> oList = new SupplierModifier().getListSupplierInfoAfterSearch(idOrCompanyName);

        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        companyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        homePageCol.setCellValueFactory(new PropertyValueFactory<>("homePage"));
        personCol.setCellValueFactory(new PropertyValueFactory<>("personRepresentative"));

        supplierTableView.setItems(oList);
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getListSupplierAfterSearch(searchTF.getText());
    }

    private boolean isCompanyNameRight() {
        String tmp = companyNameTF.getText();
        return tmp.matches("^[a-zA-Z]+[\\s]{1}[a-zA-Z]+") && tmp.length() > 3;
    }

    private void checkCompanyName() {
        if (isCompanyNameRight()) {
            hideErrorOfAddress(false);
        } else {
            hideErrorOfAddress(true);
            errorCompanyName.setText("\"" + companyNameTF.getText() + "\" is invalid.");
        }
    }

    @FXML
    private void companyNameReleased(KeyEvent event) {
        checkCompanyName();
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

    @FXML
    private void updateSupplierClicked(MouseEvent event) throws SQLException {
        Suppliers item = supplierTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to a row into table.");
            alert.showAndWait();
        } else {
            if (isAddressRight() && isCompanyNameRight() && isPhoneRight()) {
                Suppliers supplier = new Suppliers();
                supplier.setSupplierId(item.getSupplierId());
                supplier.setCompanyName(companyNameTF.getText());
                supplier.setAddress(addressTF.getText());
                supplier.setPhone(phoneTF.getText());
                supplier.setHomePage(homePageTF.getText());
                supplier.setPersonRepresentative(personTF.getText());

                if (new SupplierModifier().updateSupplier(supplier)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Notification");
                    alert.setHeaderText("Success");
                    alert.setContentText("Update supplier is successfully.");
                    alert.showAndWait();
                    getListSupplier();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setHeaderText("Error");
                alert.setContentText("Text fields are not empty.");
                alert.showAndWait();
                checkAddress();
                checkCompanyName();
                checkPhone();
            }
        }
    }

    @FXML
    private void supplierTableViewClicked(MouseEvent event) {
        Suppliers item = supplierTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to a row into table.");
            alert.showAndWait();
        } else {
            companyNameTF.setText(item.getCompanyName());
            addressTF.setText(item.getAddress());
            phoneTF.setText(item.getPhone());
            homePageTF.setText(item.getHomePage());
            personTF.setText(item.getPersonRepresentative());
        }
    }

}
