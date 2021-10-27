/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.BillStatistic;

import data_modifier.BillModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class StatisticByCancelController implements Initializable {

    LocalDate date;
    
    
    @FXML
    private Label find;
    @FXML
    private Label totalCancel;
    @FXML
    private TableView<BillStatistic> cancelTable;
    @FXML
    private TableColumn<BillStatistic, String> billId;
    @FXML
    private TableColumn<BillStatistic, Float> total;
    @FXML
    private TableColumn<BillStatistic, String> transactionDate;
    @FXML
    private TableColumn<BillStatistic, String> paymentName;
    @FXML
    private TableColumn<BillStatistic, String> statusCancel;
    @FXML
    private HBox search;
    @FXML
    private Label numberBills;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableColumn<BillStatistic, String> productId;
    @FXML
    private TableColumn<BillStatistic, String> mfgDate;
    @FXML
    private TableColumn<BillStatistic, String> expDate;
    @FXML
    private TableColumn<BillStatistic, String> quantity;
    @FXML
    private TableColumn<BillStatistic, Float> price;
    @FXML
    private TextField txtTotalNumberBill;
    @FXML
    private TextField billSuccessful;
    @FXML
    private TextField billCancel;
    @FXML
    private TableColumn<BillStatistic, String> userId;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticByCancelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    private void getShow() throws SQLException{
        
        ObservableList<BillStatistic> oList = new BillModifier().getBillCancelInfo();
        billId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        statusCancel.setCellValueFactory(new PropertyValueFactory<>("statusCancel"));
        
        cancelTable.setItems(oList);
        
    }
   
    @FXML
    private void getSearch(MouseEvent event){
    
    }
    
    
}
