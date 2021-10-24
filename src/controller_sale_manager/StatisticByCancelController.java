/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;


import data.BillStatistic;
import data_modifier.BillModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class StatisticByCancelController implements Initializable {
    
    String startDate, endDate;
    
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private Label numberBills;
    @FXML
    private Label totalCancel;
    @FXML
    private TableView<BillStatistic> cancelTable;
    @FXML
    private TableColumn<BillStatistic, String> userID;
    @FXML
    private TableColumn<BillStatistic, String> billId;
    @FXML
    private TableColumn<BillStatistic, String> transactionDate;
    @FXML
    private TableColumn<BillStatistic, String> paymentName;
    @FXML
    private TableColumn<BillStatistic, String> statusCancel;
    @FXML
    private TableColumn<BillStatistic, String> productId;
    @FXML
    private TableColumn<BillStatistic, Float> price;
    @FXML
    private TableColumn<BillStatistic, Integer> quantity;
    @FXML
    private TableColumn<BillStatistic, Float> total;
    @FXML
    private Button searchButton;
//    private TableColumn<BillStatistic, String> totalOrderAmount;

   

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
    
    private void getShow() throws SQLException {

        ObservableList<BillStatistic> oList = new BillModifier().getInfoByCancel();
        userID.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        statusCancel.setCellValueFactory(new PropertyValueFactory<>("statusCancel")); //tenbiendata
        price.setCellValueFactory(new PropertyValueFactory<>("price")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata

        

        cancelTable.setItems(oList);

    }
    
}

