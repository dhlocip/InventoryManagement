/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class StatisticByDateController implements Initializable {

    @FXML
    private DatePicker datefind;
    @FXML
    private Label find;
    @FXML
    private Label revenue;
    @FXML
    private Label numberdate;
    @FXML
    private TableView<?> dateTable;
    @FXML
    private TableColumn<?, ?> userId;
    @FXML
    private TableColumn<?, ?> billId;
    @FXML
    private TableColumn<?, ?> total;
    @FXML
    private TableColumn<?, ?> transactionDate;
    @FXML
    private TableColumn<?, ?> paymentName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
