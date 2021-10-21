/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.VBills;
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
    private DatePicker datefind;
    @FXML
    private Label find;
    @FXML
    private Label totalCancel;
    @FXML
    private TableView<VBills> cancelTable;
    @FXML
    private TableColumn<VBills, String> userID;
    @FXML
    private TableColumn<VBills, String> billId;
    @FXML
    private TableColumn<VBills, Float> total;
    @FXML
    private TableColumn<VBills, String> transactionDate;
    @FXML
    private TableColumn<VBills, String> paymentName;
    @FXML
    private TableColumn<VBills, String> statusCancel;
    @FXML
    private HBox search;
    @FXML
    private Label numberBills;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   
        try {
            getBillCancel();
        } catch (SQLException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   

       public void getBillCancel() throws SQLException {

        ObservableList<VBills> oList = new BillModifier().getInfoByCancel();
        userID.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        statusCancel.setCellValueFactory(new PropertyValueFactory<>("statusCancel")); //tenbiendata
        
        cancelTable.setItems(oList);
    }

       
       public void getBillsByTransactionDate(LocalDate tranDate) throws SQLException{
        
        
    
        ObservableList<VBills> oList = new BillModifier().getInfoByTransactionDate(date);
        
        userID.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        billId.setCellValueFactory(new PropertyValueFactory<>("billId")); //tenbiendata
        total.setCellValueFactory(new PropertyValueFactory<>("total")); //tenbiendata
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate")); //tenbiendata
        paymentName.setCellValueFactory(new PropertyValueFactory<>("paymentName")); //tenbiendata
        statusCancel.setCellValueFactory(new PropertyValueFactory<>("statusCancel")); //tenbiendata
        
        cancelTable.setItems(oList);
    }

    @FXML
    private void getFind(MouseEvent event) throws SQLException{
        
        
        
    
    }
    
    
    
    
}
