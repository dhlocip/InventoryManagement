/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class CreateProductController implements Initializable {

    @FXML
    private TextField productTF;
    @FXML
    private Label errorProductId;
    @FXML
    private TextField productNameTF;
    @FXML
    private Label errorProductName;
    @FXML
    private ComboBox<?> categoryIdComboBox;
    @FXML
    private Label errorCategoryId;
    @FXML
    private TextField priceTF;
    @FXML
    private Label errorPrice;
    @FXML
    private TableView<?> productTableView;
    @FXML
    private TableColumn<?, ?> categoryIdCol;
    @FXML
    private TableColumn<?, ?> productIdCol;
    @FXML
    private TableColumn<?, ?> productNameCol;
    @FXML
    private TableColumn<?, ?> priceCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createProductClicked(MouseEvent event) {
    }

    @FXML
    private void productTableClicked(MouseEvent event) {
    }
    
}
