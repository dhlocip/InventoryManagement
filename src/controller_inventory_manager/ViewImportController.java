/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> importTableView;
    @FXML
    private TableColumn<?, ?> importIdCol;
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
    private void searchReleased(KeyEvent event) {
    }
    
}
