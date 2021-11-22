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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
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
    String startDate, endDate;

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
    private TableColumn<BillStatistic, String> userId;
    @FXML
    private TableColumn<BillStatistic, String> productId;
    @FXML
    private TableColumn<BillStatistic, Float> price;
    @FXML
    private TableColumn<BillStatistic, Integer> quantity;
    @FXML
    private PieChart pieChart;
    @FXML
    private TextField txtOrderQuantity;
    @FXML
    private TextField txtOrderCanceled;
    @FXML
    private TextField txtxOrderSuccessful;
    @FXML
    private TextField txtTenDoThi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getShow() throws SQLException {

        ObservableList<BillStatistic> oList = new BillModifier().getInfoByCancel();
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        statusCancel.setCellValueFactory(new PropertyValueFactory<>("statusCancel")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cancelTable.setItems(oList);
    }

    private void getShowCancel(String startDate, String endDate) throws SQLException {
        ObservableList<BillStatistic> oList = new BillModifier().getBillCancelInf(startDate, endDate);
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        statusCancel.setCellValueFactory(new PropertyValueFactory<>("statusCancel")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cancelTable.setItems(oList);

    }

    @FXML
    private void getSearch(MouseEvent event) throws SQLException {

        LocalDate now = LocalDate.now();

        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the time to statistics !");
            alert.showAndWait();
        } else {

            ObservableList<String> oList = new BillModifier().getNumberCancel(startDate, endDate);
            numberBills.setText(oList.get(0));
            txtOrderCanceled.setText(oList.get(0));

            int numbillCancel = Integer.valueOf(oList.get(0));
            txtTenDoThi.setText("Pie chart for the period from "+ startDate +" to "+ endDate);
            ObservableList<String> oListTotal = new BillModifier().getTotalCancel(startDate, endDate);
            totalCancel.setText(oListTotal.get(0));
            getShowCancel(startDate, endDate);
            ObservableList<String> oListBill = new BillModifier().getNumbers(startDate, endDate);
            txtOrderQuantity.setText(oListBill.get(0));
            ObservableList<String> oListSuccessful = new BillModifier().getNumberDate(startDate, endDate);
            txtxOrderSuccessful.setText(oListSuccessful.get(0));

            if (numbillCancel == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("No canceled invoices during this time!");
                alert.showAndWait();
            }

            startDatePicker.setValue(null);
            endDatePicker.setValue(null);

            try {
                int OrderCanceled = Integer.valueOf(txtOrderCanceled.getText());
                int OrderSuccessful = Integer.valueOf(txtxOrderSuccessful.getText());
                PieChart.Data Canceled = new PieChart.Data("Canceled", OrderCanceled);
                PieChart.Data Successful = new PieChart.Data("Successful", OrderSuccessful);
                pieChart.getData().clear();
                pieChart.getData().addAll(Canceled, Successful);
            } catch (Exception e) {
            }

        }

    }

    @FXML
    private void startDateAction(ActionEvent event) {
        LocalDate start = startDatePicker.getValue();
        startDate = String.valueOf(start);
    }

    @FXML
    private void endDateAction(ActionEvent event) {
        LocalDate end = endDatePicker.getValue();
        endDate = String.valueOf(end);
    }

}
