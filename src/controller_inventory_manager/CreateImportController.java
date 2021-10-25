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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class CreateImportController implements Initializable {

    @FXML
    private ComboBox<?> supplierIdComboBox;
    @FXML
    private DatePicker ImportDatePicker;
    @FXML
    private Label errorImportDate;
    @FXML
    private ComboBox<?> productIdComboBox;
    @FXML
    private Spinner<?> quantitySpinner;
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
    private TableView<?> importTableView;
    @FXML
    private TableColumn<?, ?> importStockIdCol;
    @FXML
    private TableColumn<?, ?> supplierIdCol;
    @FXML
    private TableColumn<?, ?> importDateCol;
    @FXML
    private TableColumn<?, ?> productIdCol;
    @FXML
    private TableColumn<?, ?> quantityCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private TableColumn<?, ?> mfgDateCol;
    @FXML
    private TableColumn<?, ?> expDateCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createImportStockClicked(MouseEvent event) {
    }

    @FXML
    private void createImportStockDetailClicked(MouseEvent event) {
    }
    
}
