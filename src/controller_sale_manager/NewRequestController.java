/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;




import data.VNewRequest;
import data_modifier.NewRequestModilfier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
public class NewRequestController implements Initializable {

    @FXML
    private TableView<VNewRequest> newRequestTable;
    @FXML
    private TableColumn<VNewRequest, String> newRequestId;
    @FXML
    private TableColumn<VNewRequest, String> newproductName;
    @FXML
    private TableColumn<VNewRequest, Integer> quantity;
    @FXML
    private TableColumn<VNewRequest, String> userId;
    @FXML
    private TableColumn<VNewRequest, String> startDate;
    @FXML
    private TableColumn<VNewRequest, String> statusVerify;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(NewRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    } 
    
    private void getShow() throws SQLException{
        
        ObservableList<VNewRequest> oList = new NewRequestModilfier().getNewRequestInfo();
        newRequestId.setCellValueFactory(new PropertyValueFactory<>("newRequestId")); //tenbiendata
        newproductName.setCellValueFactory(new PropertyValueFactory<>("newProductName")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); //tenbiendata
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        statusVerify.setCellValueFactory(new PropertyValueFactory<>("statuVerify")); //tenbiendata
        
        newRequestTable.setItems(oList);
        
    }
    
    
    
    
}
