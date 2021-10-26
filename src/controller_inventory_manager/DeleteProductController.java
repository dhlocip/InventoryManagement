/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.Product;
import data_modifier.BillModifier;
import data_modifier.EventModifier;
import data_modifier.ImportStockDetailModifier;
import data_modifier.ImportStockModifier;
import data_modifier.ProductModifier;
import data_modifier.RequestModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class DeleteProductController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> categoryIdCol;
    @FXML
    private TableColumn<Product, String> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, String> priceCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getListProduct();
        } catch (SQLException ex) {
            Logger.getLogger(ViewProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // get list product info
    private void getListProduct() throws SQLException {
        ObservableList<Product> oList = new ProductModifier().getListProduct();

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(oList);
    }

    // get list product info after search
    private void getListProductAfterSearch(String idOrName) throws SQLException {
        ObservableList<Product> oList = new ProductModifier().getListProductAfterSearch(idOrName);

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(oList);
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getListProductAfterSearch(searchTF.getText());
    }

    @FXML
    private void productTableClicked(MouseEvent event) throws SQLException {
        Product item = productTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on the line different null.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the line.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (new ImportStockDetailModifier().deleteImportStockDetail(item.getProductId())
                        && new EventModifier().deleteEventDetail(item.getProductId())
                        && new BillModifier().deleteBillDetail(item.getProductId())
                        && new RequestModifier().deleteRequestDetail(item.getProductId())) {
                    new ProductModifier().deleteProduct(item.getProductId());
                    getListProduct();
                }
            }
            getListProduct();
        }
    }

}
