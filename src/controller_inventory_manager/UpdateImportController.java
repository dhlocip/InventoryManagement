/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.VImportStock;
import data_modifier.ProductModifier;
import data_modifier.SupplierModifier;
import data_modifier.VImportStockModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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
public class UpdateImportController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private ComboBox<String> supplierIdComboBox;
    @FXML
    private DatePicker importDatePicker;
    private Label errorImportDate;
    @FXML
    private ComboBox<String> productIdComboBox;
    @FXML
    private Spinner<String> quantitySpinner;
    @FXML
    private TextField priceTF;
    @FXML
    private Label errorPrice;
    @FXML
    private DatePicker mfgDatePicker;
    private Label errorMfgDate;
    @FXML
    private DatePicker expDatePicker;
    private Label errorExpDate;
    @FXML
    private TableView<VImportStock> importTableView;
    @FXML
    private TableColumn<VImportStock, String> importStockIdCol;
    @FXML
    private TableColumn<VImportStock, String> supplierIdCol;
    @FXML
    private TableColumn<VImportStock, String> importDateCol;
    @FXML
    private TableColumn<VImportStock, String> productIdCol;
    @FXML
    private TableColumn<VImportStock, Integer> quantityCol;
    @FXML
    private TableColumn<VImportStock, Double> priceCol;
    @FXML
    private TableColumn<VImportStock, String> mfgDateCol;
    @FXML
    private TableColumn<VImportStock, String> expDateCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        hideErrorOfExpDate(false);
        hideErrorOfMfgDate(false);
        hideErrorOfPrice(false);
        hideErrorOfImportDate(false);

        try {
            getListImportStock();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateImportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getListImportStock() throws SQLException {
        ObservableList<VImportStock> oList = new VImportStockModifier().getListVImportStock();

        importStockIdCol.setCellValueFactory(new PropertyValueFactory<>("importStockId"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        importDateCol.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        mfgDateCol.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDateCol.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        importTableView.setItems(oList);
    }

    private void getListImportStockAfterSearch(String idOrDate) throws SQLException {
        ObservableList<VImportStock> oList = new VImportStockModifier().getListVImportStockAfterSearch(idOrDate);

        importStockIdCol.setCellValueFactory(new PropertyValueFactory<>("importStockId"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        importDateCol.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        mfgDateCol.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDateCol.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        importTableView.setItems(oList);
    }

    private void hideErrorOfExpDate(boolean value) {
        errorExpDate.setVisible(value);
        errorExpDate.managedProperty().bind(errorExpDate.visibleProperty());
    }

    private void hideErrorOfMfgDate(boolean value) {
        errorMfgDate.setVisible(value);
        errorMfgDate.managedProperty().bind(errorMfgDate.visibleProperty());
    }

    private void hideErrorOfPrice(boolean value) {
        errorPrice.setVisible(value);
        errorPrice.managedProperty().bind(errorPrice.visibleProperty());
    }

    private void hideErrorOfImportDate(boolean value) {
        errorImportDate.setVisible(value);
        errorImportDate.managedProperty().bind(errorImportDate.visibleProperty());
    }

    private void setProductIdComboBox() throws SQLException {
        ObservableList<String> oList = new ProductModifier().getListProductId();
        productIdComboBox.setItems(oList);

        productIdComboBox.setOnAction((t) -> {
            productIdComboBox.getValue();
        });
    }

    private void setSupplierIdComboBox() throws SQLException {
        ObservableList<String> oList = new SupplierModifier().getListSupplierId();
        supplierIdComboBox.setItems(oList);

        supplierIdComboBox.setOnAction((t) -> {
            supplierIdComboBox.getValue();
        });
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getListImportStockAfterSearch(searchTF.getText());
    }

    @FXML
    private void updateImportStockClicked(MouseEvent event) {
        VImportStock item = importTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to a row into table.");
            alert.showAndWait();
        } else {

        }
    }

    @FXML
    private void updateImportStockDetailClicked(MouseEvent event) {
    }

    @FXML
    private void importTableClicked(MouseEvent event) {
    }

    private boolean isPriceRight() {
        String tmp = priceTF.getText();
        return tmp.matches("^[\\d]+[.]?[\\d]+");
    }

    private void checkPrice() {
        if (isPriceRight()) {
            hideErrorOfPrice(false);
        } else {
            hideErrorOfPrice(true);
            errorPrice.setText("\'" + priceTF.getText() + "\' is invalid.");
        }
    }

    @FXML
    private void priceReleased(KeyEvent event) {
        checkPrice();
    }

}
