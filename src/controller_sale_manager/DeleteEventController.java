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
public class DeleteEventController implements Initializable {

    @FXML
    private DatePicker findDate;
    @FXML
    private Label find;
    @FXML
    private TableView<?> eventTable;
    @FXML
    private TableColumn<?, ?> eventID;
    @FXML
    private TableColumn<?, ?> eventName;
    @FXML
    private TableColumn<?, ?> productId;
    @FXML
    private TableColumn<?, ?> distcount;
    @FXML
    private TableColumn<?, ?> startDate;
    @FXML
    private TableColumn<?, ?> endDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
