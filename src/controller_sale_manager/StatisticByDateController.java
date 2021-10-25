
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
public class StatisticByDateController implements Initializable {

    String startDate, endDate, userID;
    
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private Label revenue;
    @FXML
    private Label numberBills;
    @FXML
    private TableColumn<BillStatistic, String> userId;
    @FXML
    private TableColumn<BillStatistic, String> transactionDate;
    @FXML
    private TableColumn<BillStatistic, String> paymentName;
    @FXML
    private TableColumn<BillStatistic, String> productId;
    @FXML
    private TableColumn<BillStatistic, Float> price;
    @FXML
    private TableColumn<BillStatistic, Float> total;
    @FXML
    private TableView<BillStatistic> BillDateTable;
    @FXML
    private TableColumn<BillStatistic, String> billId;
    @FXML
    private TableColumn<BillStatistic, Integer> quantity;
    @FXML
    private Button searchDate;
    @FXML
    private LineChart<String, Number> dateLineChart;
    

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
        

        BillDateTable.setItems(oList);

    }
        
        private void getShowDate(String startDate, String endDate) throws SQLException {

        ObservableList<BillStatistic> oList = new BillModifier().getBillDateInfo(startDate, endDate);
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        price.setCellValueFactory(new PropertyValueFactory<>("price")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        

        BillDateTable.setItems(oList);

    }
       
        
        private void getLineChart(String startDate, String endDate) throws SQLException {
            
        ObservableList<XYChart.Data<String, Number>> CharList = new BillModifier().getDateLineChart(startDate, endDate);

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().addAll(CharList);

        dateLineChart.getData().clear();
        dateLineChart.getData().add(series);
        
        dateLineChart.setTitle("Revenue");
        series.setName("Daily revenue trend chart.");
    }


    @FXML
    private void getStartDate(ActionEvent event) {
        
        LocalDate start = txtStartDate.getValue();
        startDate = String.valueOf(start);
    }

    @FXML
    private void getEndDate(ActionEvent event) {
        
        LocalDate end = txtEndDate.getValue();
        endDate = String.valueOf(end);
        
    }

    @FXML
    private void searchDateAction(ActionEvent event) throws SQLException {
        
        ObservableList<String> oList =  new BillModifier().getNumberCancel(startDate,endDate);
            numberBills.setText(oList.get(0));
            ObservableList<String> oListTotal = new BillModifier().getTotalDate(startDate, endDate);
            revenue.setText(oListTotal.get(0));
            getShowDate(startDate, endDate);
            getLineChart(startDate,endDate);
            
    }
    
    
    
    
}
