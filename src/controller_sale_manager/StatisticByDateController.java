
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
//import javafx.css.converter.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class StatisticByDateController implements Initializable {

    String startDate, endDate, userID;

    @FXML
    private Label find;
    @FXML
    private Label revenue;
    @FXML
    private TableView<BillStatistic> dateTable;
    @FXML
    private TableColumn<BillStatistic, String> userId;
    @FXML
    private TableColumn<BillStatistic, String> billId;
    @FXML
    private TableColumn<BillStatistic, Float> total;
    @FXML
    private TableColumn<BillStatistic, String> transactionDate;
    @FXML
    private TableColumn<BillStatistic, String> paymentName;
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
    private TableColumn<BillStatistic, Float> price;
    @FXML
    private TableColumn<BillStatistic, Integer> quantity;
    @FXML
    private LineChart<String, Number> lineChart;

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

    private void getShow() throws SQLException {

        ObservableList<BillStatistic> oList = new BillModifier().getInfByDate();
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        price.setCellValueFactory(new PropertyValueFactory<>("price")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata

        dateTable.setItems(oList);

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

        dateTable.setItems(oList);

    }

    private void getLineChart(String startDate, String endDate) throws SQLException {

        ObservableList<XYChart.Data<String, Number>> CharList = new BillModifier().getDateLineChart(startDate, endDate);

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().addAll(CharList);

        lineChart.getData().clear();
        lineChart.getData().add(series);

        lineChart.setTitle("Revenue");
        series.setName("Daily revenue trend chart.");
    }

    @FXML
    private void getSearch(MouseEvent event) throws SQLException {

        LocalDate now = LocalDate.now();

        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("chon ngay");
            alert.showAndWait();
        } else {

            ObservableList<String> oList = new BillModifier().getNumberCancel(startDate, endDate);
            numberBills.setText(oList.get(0));
            ObservableList<String> oListTotal = new BillModifier().getTotalDate(startDate, endDate);
            revenue.setText(oListTotal.get(0));
            getShowDate(startDate, endDate);
            getLineChart(startDate, endDate);

            int numbill = Integer.valueOf(oList.get(0));
            if (numbill == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Khong co hoa don duoc ban trong thoi gian nay.");
                alert.showAndWait();
            }
            startDatePicker.setValue(null);
            endDatePicker.setValue(null);
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
