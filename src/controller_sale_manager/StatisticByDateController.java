
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.VBills;
import data_modifier.BillModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<VBills> dateTable;
    @FXML
    private TableColumn<VBills, String> userId;
    @FXML
    private TableColumn<VBills, String> billId;
    @FXML
    private TableColumn<VBills, Float> total;
    @FXML
    private TableColumn<VBills, String> transactionDate;
    @FXML
    private TableColumn<VBills, String> paymentName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticByDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void getShow() throws SQLException{
        
        ObservableList<VBills> oList = new BillModifier().getBillsInfo();
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata

        
        dateTable.setItems(oList);
        
    }
    
    
}
