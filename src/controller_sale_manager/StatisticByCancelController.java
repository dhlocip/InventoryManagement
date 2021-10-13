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
public class StatisticByCancelController implements Initializable {

    @FXML
    private DatePicker datefind;
    @FXML
    private Label find;
    @FXML
    private Label number;
    @FXML
    private Label totalCancel;
    @FXML
    private TableView<?> cancelTable;
    @FXML
    private TableColumn<?, ?> userID;
    @FXML
    private TableColumn<?, ?> billId;
    @FXML
    private TableColumn<?, ?> total;
    @FXML
    private TableColumn<?, ?> transactionDate;
    @FXML
    private TableColumn<?, ?> paymentName;
    @FXML
    private TableColumn<?, ?> statusCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
