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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class DeleteSupplierController implements Initializable {

    @FXML
    private TextField searchTF;
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
    private TableView<Suppliers> supplierTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getListSupplier();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    @FXML
    private void supplierTableViewClicked(MouseEvent event) throws SQLException {
        Suppliers item = supplierTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to a row into table.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the line.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new SupplierModifier().deleteSupplier(item.getSupplierId());
                getListSupplier();
            }
        }
    }

}
