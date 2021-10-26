/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import controller_app.UIDashboardAdminController;
import controller_app.UIDashboardInventoryManagerController;
import controller_app.UIDashboardSaleManagerController;
import data.ImportStock;
import data.ImportStockDetail;
import data.VImportStock;
import data_modifier.ImportStockDetailModifier;
import data_modifier.ImportStockModifier;
import data_modifier.ProductModifier;
import data_modifier.SupplierModifier;
import data_modifier.VImportStockModifier;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.SpinnerValueFactory;
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
public class CreateImportController implements Initializable {

    String lUserId;
    Integer lQuantity;

    @FXML
    private ComboBox<String> supplierIdComboBox;
    @FXML
    private Label errorImportDate;
    @FXML
    private ComboBox<String> productIdComboBox;
    @FXML
    private Spinner<Integer> quantitySpinner;
    @FXML
    private TextField priceTF;
    @FXML
    private Label errorPrice;
    @FXML
    private DatePicker mfgDatePicker;
    @FXML
    private Label errorMfgDate;
    @FXML
    private DatePicker expDatePicker;
    @FXML
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
    @FXML
    private DatePicker importDatePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //        get userId
        if (UIDashboardAdminController.gPosition != null
                && UIDashboardInventoryManagerController.gPosition == null
                && UIDashboardSaleManagerController.gPosition == null) {
            lUserId = UIDashboardAdminController.gUserId;

        } else if (UIDashboardAdminController.gPosition == null
                && UIDashboardInventoryManagerController.gPosition != null
                && UIDashboardSaleManagerController.gPosition == null) {

            lUserId = UIDashboardInventoryManagerController.gUserId;
        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
        }

        hideErrorOfPrice(false);
        hideErrorOfImportDate(false);
        hideErrorOfMfgDate(false);
        hideErrorOfExpDate(false);

        try {
            getListImportStock();
            setSupplierIdComboBox();
            setProductIdComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateImportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setQuantitySpinner();
    }

    private void setQuantitySpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactory.setValue(1);
        quantitySpinner.setEditable(true);
        quantitySpinner.setValueFactory(valueFactory);
        lQuantity = quantitySpinner.getValue();

        quantitySpinner.valueProperty().addListener((o) -> {
            lQuantity = quantitySpinner.getValue();
        });

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

    private void hideErrorOfPrice(boolean value) {
        errorPrice.setVisible(value);
        errorPrice.managedProperty().bind(errorPrice.visibleProperty());
    }

    private void hideErrorOfImportDate(boolean value) {
        errorImportDate.setVisible(value);
        errorImportDate.managedProperty().bind(errorImportDate.visibleProperty());
    }

    private void hideErrorOfMfgDate(boolean value) {
        errorMfgDate.setVisible(value);
        errorMfgDate.managedProperty().bind(errorMfgDate.visibleProperty());
    }

    private void hideErrorOfExpDate(boolean value) {
        errorExpDate.setVisible(value);
        errorExpDate.managedProperty().bind(errorExpDate.visibleProperty());
    }

    private void setProductIdComboBox() throws SQLException {
        ObservableList<String> oList = new ProductModifier().getListProductId();
        productIdComboBox.setItems(oList);
        productIdComboBox.setValue(oList.get(0));

        productIdComboBox.setOnAction((t) -> {
            productIdComboBox.getValue();
        });
    }

    private void setSupplierIdComboBox() throws SQLException {
        ObservableList<String> oList = new SupplierModifier().getListSupplierId();
        supplierIdComboBox.setItems(oList);
        supplierIdComboBox.setValue(oList.get(0));

        supplierIdComboBox.setOnAction((t) -> {
            supplierIdComboBox.getValue();
        });
    }

    @FXML
    private void createImportStockClicked(MouseEvent event) throws SQLException {
        if (importDatePicker.getValue() != null) {
            ImportStock importS = new ImportStock();
            importS.setUserId(lUserId);
            importS.setSupplierId(supplierIdComboBox.getValue());
            importS.setImportDate((importDatePicker.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            if (new ImportStockModifier().createImportStock(importS)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Create import stock is successfully.");
                alert.showAndWait();
                getListImportStock();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Text fields are not empty.");
            alert.showAndWait();
            hideErrorOfImportDate(true);
            errorImportDate.setText("\'Import Date\' is not empty.");
        }
    }

    @FXML
    private void createImportStockDetailClicked(MouseEvent event) throws SQLException {
        if (priceTF.getText() != null
                && mfgDatePicker.getValue() != null
                && expDatePicker.getValue() != null) {
            ImportStockDetail impDetail = new ImportStockDetail();
            impDetail.setImportStockId(new ImportStockModifier().getMaxImportStockId());
            impDetail.setProductId(productIdComboBox.getValue());
            impDetail.setQuantity(quantitySpinner.getValue());
            System.out.println(quantitySpinner.getValue());
            impDetail.setPrice(Double.parseDouble(priceTF.getText()));
            impDetail.setMfgDate((mfgDatePicker.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            impDetail.setExpDate((expDatePicker.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            if (!new ImportStockDetailModifier().productIdIsExists(impDetail.getProductId(), impDetail.getImportStockId())) {
                if (new ImportStockDetailModifier().createImportStock(impDetail)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Notification");
                    alert.setHeaderText("Success");
                    alert.setContentText("Create import stock detail is successfully.");
                    alert.showAndWait();
                    getListImportStock();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setHeaderText("Error");
                alert.setContentText("\'" + productIdComboBox.getValue() + "\' is exists. \nPlease next to update import stock detail.");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Text fields are not empty.");
            alert.showAndWait();
            checkPrice();
            checkMfgDate();
            checkExpDate();
        }
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

    @FXML
    private void importDateAction(ActionEvent event) {
        hideErrorOfImportDate(false);
    }

    private void checkMfgDate() {
        if (mfgDatePicker.getValue() == null) {
            hideErrorOfMfgDate(true);
            errorMfgDate.setText("\'MFG Date\' is not empty.");
        } else {
            hideErrorOfMfgDate(false);
        }
    }

    @FXML
    private void mfgDateAction(ActionEvent event) {
        checkMfgDate();
    }

    private void checkExpDate() {
        if (expDatePicker.getValue() == null) {
            hideErrorOfExpDate(true);
            errorExpDate.setText("\'EXP Date\' is not empty.");
        } else {
            hideErrorOfExpDate(false);
        }
    }

    @FXML
    private void expDateAction(ActionEvent event) {
        checkExpDate();
    }

}
