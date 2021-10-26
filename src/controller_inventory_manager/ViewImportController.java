/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.VImportStock;
import data_modifier.VImportStockModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewImportController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TableView<VImportStock> importTableView;
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
    private TableColumn<VImportStock, String> importStockIdCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            getListImportStock();
        } catch (SQLException ex) {
            Logger.getLogger(ViewImportController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getListImportStockAfterSearch(searchTF.getText());
    }
    
}
