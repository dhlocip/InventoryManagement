/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.NewRequest;
import data.NewRequestDetail;

import java.net.URL;
import java.util.ResourceBundle;
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
    private TableView<NewRequest> newRequestTable;
    @FXML
    private TableColumn<NewRequest, String> newRequestId;
    @FXML
    private TableColumn<NewRequestDetail, String> newproductName;
    @FXML
    private TableColumn<NewRequestDetail, Integer> quantity;
    @FXML
    private TableColumn<NewRequest, String> userId;
    @FXML
    private TableColumn<NewRequest, String> startDate;
    @FXML
    private TableColumn<NewRequest, String> statusVerify;

    
    private ObservableList<NewRequest> newRequestList;
    private ObservableList<NewRequestDetail> newRequestDetailList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        newRequestList =FXCollections.observableArrayList(
        //new NewRequest(newRequestId, userId, startDate, statuVerify)
        
        new NewRequest("1", "ID001", "11/11/2020", "Yes"),
        new NewRequest("2", "ID002", "12/12/2020", "No")       
        );
        newRequestDetailList = FXCollections.observableArrayList(
        //new NewRequestDetail(newRequestId, newProductName, 0)
        
        new NewRequestDetail("PR001", "One", 1),
        new NewRequestDetail("PR002", "Two", 2)
        );
        newRequestId.setCellValueFactory(new PropertyValueFactory<NewRequest, String>("requestId"));
        userId.setCellValueFactory(new PropertyValueFactory<NewRequest, String>("userId"));
        startDate.setCellValueFactory(new PropertyValueFactory<NewRequest, String>("startDate"));
        statusVerify.setCellValueFactory(new PropertyValueFactory<NewRequest, String>("statusVerify"));
        newproductName.setCellValueFactory(new PropertyValueFactory<NewRequestDetail, String>("productId"));
        quantity.setCellValueFactory(new PropertyValueFactory<NewRequestDetail, Integer>("quantity"));
        newRequestTable.setItems(newRequestList);
        //RequestTable.setItems(requestDetailList);
                
    }    
    
}
