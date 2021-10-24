
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.BillDetail;
import data.BillStatistic;
import data.Bills;
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
    private DatePicker txtStartDate;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private Label revenue;
    @FXML
    private Label numberBills;
    @FXML
    private TableColumn<Bills, String> userId;
    @FXML
    private TableColumn<Bills, String> transactionDate;
    @FXML
    private TableColumn<Bills, String> paymentName;
    @FXML
    private TableColumn<BillDetail, String> productId;
    @FXML
    private TableColumn<BillDetail, Float> price;
    @FXML
    private TableColumn<BillDetail, Float> total;
    @FXML
    private TableView<Bills> BillDateTable;
    @FXML
    private TableColumn<BillStatistic, String> billId;
    @FXML
    private TableView<BillStatistic> revenuetable;
    @FXML
    private TableColumn<BillStatistic, Integer> quantity;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticByDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    
    
        private void getShow() throws SQLException {

        ObservableList<BillStatistic> oList = new BillModifier().getInfoByDate();
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        price.setCellValueFactory(new PropertyValueFactory<>("price")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        

        revenuetable.setItems(oList);

    }
    
    
    
    
}
