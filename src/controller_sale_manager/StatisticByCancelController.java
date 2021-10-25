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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    private LineChart<String, Number> cancelLineChart;
    @FXML
    private PieChart cancelPieChart;
    @FXML
    private TextField txtOrderQuantity;
    @FXML
    private TextField txtOrderCanceled;
    @FXML
    private TextField txtxOrderSuccessful;

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

    private void getShowCancel(String startDate, String endDate) throws SQLException {

        ObservableList<BillStatistic> oList = new BillModifier().getBillCancelInfo(startDate, endDate);
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

    @FXML
    private void getSearch(ActionEvent event) throws SQLException {
        ObservableList<String> oList = new BillModifier().getNumberCancel(startDate, endDate);
        numberBills.setText(oList.get(0));
        txtOrderCanceled.setText(oList.get(0));

        if (Integer.valueOf(numberBills.getText()) != 0) {
            numberBills.setText(oList.get(0));
            txtOrderCanceled.setText(oList.get(0));
            ObservableList<String> oListTotal = new BillModifier().getTotalCancel(startDate, endDate);
            totalCancel.setText(oListTotal.get(0));
            getShowCancel(startDate, endDate);
            ObservableList<String> oListBill = new BillModifier().getNumbers(startDate, endDate);
            txtOrderQuantity.setText(oListBill.get(0));
            ObservableList<String> oListSuccessful = new BillModifier().getNumberDate(startDate, endDate);
            txtxOrderSuccessful.setText(oListSuccessful.get(0));

            try {
                int OrderCanceled = Integer.valueOf(txtOrderCanceled.getText());
                int OrderSuccessful = Integer.valueOf(txtxOrderSuccessful.getText());
                PieChart.Data Canceled = new PieChart.Data("Canceled", OrderCanceled);
                PieChart.Data Successful = new PieChart.Data("Successful", OrderSuccessful);
                cancelPieChart.getData().clear();
                cancelPieChart.getData().addAll(Canceled, Successful);
            } catch (Exception e) {
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No canceled invoices from " + startDate + " to " + endDate);
            alert.showAndWait();
        }
    }

    @FXML
    private void startDateAction(ActionEvent event) {

        LocalDate start = txtStartDate.getValue();
        startDate = String.valueOf(start);
    }

    @FXML
    private void endDateAction(ActionEvent event) {
        LocalDate end = txtEndDate.getValue();
        endDate = String.valueOf(end);

    }

}
