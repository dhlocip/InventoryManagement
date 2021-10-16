/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;


import data.VRequest;
import data_modifier.RequestModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author sa
 */
public class RequestController implements Initializable {

    @FXML
    private TableView<VRequest> RequestTable;
    
    
    @FXML
    private TableColumn<VRequest, String> requestId;
    @FXML
    private TableColumn<VRequest, String> productId;
    @FXML
    private TableColumn<VRequest, Integer> quantity;
    @FXML
    private TableColumn<VRequest, String> userId;
    @FXML
    private TableColumn<VRequest, String> startDate;
    @FXML
    private TableColumn<VRequest, String> statusVerify;

    
    
    
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
     private void getShow() throws SQLException{
        
        ObservableList<VRequest> oList = new RequestModifier().getRequestInfo();
        requestId.setCellValueFactory(new PropertyValueFactory<>("requestId")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        userId.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        statusVerify.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        
        RequestTable.setItems(oList);
        
    }
    
}
