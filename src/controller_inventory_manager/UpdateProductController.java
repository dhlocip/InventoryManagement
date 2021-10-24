/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.Product;
import data_modifier.CategoryModifier;
import data_modifier.ProductModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
public class UpdateProductController implements Initializable {

    @FXML
    private TextField productIdTF;
    @FXML
    private Label errorProductId;
    @FXML
    private TextField productNameTF;
    @FXML
    private Label errorProductName;
    @FXML
    private Label errorCategoryId;
    @FXML
    private TextField priceTF;
    @FXML
    private Label errorPrice;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> categoryIdCol;
    @FXML
    private TableColumn<Product, String> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Double> priceCol;
    @FXML
    private ComboBox<String> categoryIdComboBox;
    @FXML
    private TextField searchTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // hide errors
        hideErrorOfCategoryId(false);
        hideErrorOfPrice(false);
        hideErrorOfProductId(false);
        hideErrorOfProductName(false);

        try {
            setCategoryId();
            getListProduct();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void hideErrorOfProductId(boolean value) {
        errorProductId.setVisible(value);
        errorProductId.managedProperty().bind(errorProductId.visibleProperty());
    }

    private void hideErrorOfProductName(boolean value) {
        errorProductName.setVisible(value);
        errorProductName.managedProperty().bind(errorProductName.visibleProperty());
    }

    private void hideErrorOfCategoryId(boolean value) {
        errorCategoryId.setVisible(value);
        errorCategoryId.managedProperty().bind(errorCategoryId.visibleProperty());
    }

    private void hideErrorOfPrice(boolean value) {
        errorPrice.setVisible(value);
        errorPrice.managedProperty().bind(errorPrice.visibleProperty());
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

//  set categoryId
    private void setCategoryId() throws SQLException {
        ObservableList<String> oList = new CategoryModifier().getListCategoryId();
        categoryIdComboBox.setItems(oList);

        categoryIdComboBox.setOnAction((t) -> {
            categoryIdComboBox.getValue();
        });

    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getListProductAfterSearch(searchTF.getText());
    }

    @FXML
    private void updateProductClicked(MouseEvent event) throws SQLException {
        Product item = productTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on the line different null.");
            alert.showAndWait();
        } else {
            Product product = new Product();
            product.setProductId(item.getProductId());
            product.setProductName(productNameTF.getText());
            product.setCategoryId(categoryIdComboBox.getValue());
            product.setPrice(Double.parseDouble(priceTF.getText()));
            if (new ProductModifier().updateProduct(product)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Update product is successfully.");
                alert.showAndWait();
                getListProduct();
            }
        }
    }

    @FXML
    private void productTableViewClicked(MouseEvent event) {
        Product item = productTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on the line different null.");
            alert.showAndWait();
        } else {
            productIdTF.setText(item.getProductId());
            productNameTF.setText(item.getProductName());
            categoryIdComboBox.setValue(item.getCategoryId());
            priceTF.setText(String.valueOf(item.getPrice()));
        }
    }

    private boolean isProductNameRight() {
        String tmp = productNameTF.getText();
        return tmp.matches("^[\\w\\s]+") && tmp.length() > 2;
    }

    private void checkProductName() {
        if (isProductNameRight()) {
            hideErrorOfProductName(false);
        } else {
            hideErrorOfProductName(true);
            errorProductName.setText("\'" + productNameTF.getText() + "\' is invalid.");
        }
    }

    @FXML
    private void productNameReleased(KeyEvent event) {
        checkProductName();
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
