/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import controller_app.UIDashboardAdminController;
import controller_app.UIDashboardInventoryManagerController;
import controller_app.UIDashboardSaleManagerController;
import data.Inventory;
import data_modifier.CategoryModifier;
import data_modifier.InventoryModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class InventoryByCategoryController implements Initializable {

    String lUserId;

    @FXML
    private ComboBox<String> categoryIdComboBox;
    @FXML
    private TableView<Inventory> inventoryTableView;
    @FXML
    private TableColumn<Inventory, String> categoryIdCol;
    @FXML
    private TableColumn<Inventory, String> productIdCol;
    @FXML
    private TableColumn<Inventory, String> productNameCol;
    @FXML
    private TableColumn<Inventory, Integer> quantityCol;
    @FXML
    private TableColumn<Inventory, String> mfgDateCol;
    @FXML
    private TableColumn<Inventory, String> expDateCol;
    @FXML
    private BarChart<String, Number> inventoryBarChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //        get userId
        if (UIDashboardAdminController.gPosition != null
                && UIDashboardInventoryManagerController.gPosition == null
                && UIDashboardSaleManagerController.gPosition == null) {
            lUserId = UIDashboardAdminController.gUserId;

        } else if (UIDashboardAdminController.gPosition == null
                && UIDashboardInventoryManagerController.gPosition != null
                && UIDashboardSaleManagerController.gPosition == null) {

            lUserId = UIDashboardInventoryManagerController.gUserId;
        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
        }

        try {
            setCategoryId();
            getListInventory();
            getInventoryBarChart();
        } catch (SQLException ex) {
            Logger.getLogger(InventoryByDateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getInventoryBarChart() throws SQLException {
        ObservableList<XYChart.Data<String, Number>> data = new InventoryModifier().getBarChartByDate();

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.getData().addAll(data);

        inventoryBarChart.getData().clear();
        inventoryBarChart.getData().add(series);
        inventoryBarChart.setTitle("Inventory by category");
        xAxis.setLabel("ProductName");
        yAxis.setLabel("Quantity");
    }

    private void getInventoryBarChartByCategoryId(String categoryId) throws SQLException {
        ObservableList<XYChart.Data<String, Number>> data = new InventoryModifier().getBarChartByCategory(categoryId);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.getData().addAll(data);

        inventoryBarChart.getData().clear();
        inventoryBarChart.getData().add(series);
        inventoryBarChart.setTitle("Inventory by date");
        xAxis.setLabel("ProductName");
        yAxis.setLabel("Quantity");
    }

    //  set categoryId
    private void setCategoryId() throws SQLException {
        ObservableList<String> oList = new CategoryModifier().getListCategoryId();
        categoryIdComboBox.setItems(oList);
    }

    private void getListInventory() throws SQLException {
        ObservableList<Inventory> oList = new InventoryModifier().getListInventory();

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        mfgDateCol.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDateCol.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        inventoryTableView.setItems(oList);
    }

    private void getListInventoryAfterSearch(String categoryId) throws SQLException {
        ObservableList<Inventory> oList = new InventoryModifier().getListInventoryAfterSearch(categoryId);

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        mfgDateCol.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDateCol.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        inventoryTableView.setItems(oList);
    }

    @FXML
    private void exportReportClicked(MouseEvent event) throws SQLException, IOException {
        if (categoryIdComboBox.getValue() != null) {
            new InventoryModifier().exportInventoryByCategoryId(lUserId, categoryIdComboBox.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Success");
            alert.setContentText("Exported excel file successfully.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to \'Category ID\'.");
            alert.showAndWait();
        }
    }

    @FXML
    private void categoryIdAction(ActionEvent event) throws SQLException {
        String categoryId = categoryIdComboBox.getValue();
        getListInventoryAfterSearch(categoryId);
        getInventoryBarChartByCategoryId(categoryId);
    }

}
