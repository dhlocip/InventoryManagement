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
public class CreateProductController implements Initializable {

    @FXML
    private TextField productNameTF;
    @FXML
    private Label errorProductName;
    @FXML
    private ComboBox<String> categoryIdComboBox;
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
    private TableColumn<Product, String> priceCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // hide errors
        hideErrorOfCategoryId(false);
        hideErrorOfPrice(false);
        hideErrorOfProductName(false);

        try {
            setCategoryId();
            getListProduct();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

//  set categoryId
    private void setCategoryId() throws SQLException {
        ObservableList<String> oList = new CategoryModifier().getListCategoryId();
        categoryIdComboBox.setItems(oList);
        categoryIdComboBox.setValue(oList.get(0));

        categoryIdComboBox.setOnAction((t) -> {
            categoryIdComboBox.getValue();
        });

    }

    @FXML
    private void createProductClicked(MouseEvent event) throws SQLException {
        if (isProductNameRight() && isPriceRight()) {
            Product product = new Product();
            product.setProductName(productNameTF.getText());
            product.setCategoryId(categoryIdComboBox.getValue());
            product.setPrice(Double.parseDouble(priceTF.getText()));

            if (new ProductModifier().createProduct(product)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Create product is successfully.");
                alert.showAndWait();
                getListProduct();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Text fields are not empty.");
            alert.showAndWait();
            checkPrice();
            checkProductName();
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
