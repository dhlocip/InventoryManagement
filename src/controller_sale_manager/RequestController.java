/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;


import com.sun.jdi.connect.spi.Connection;
import data.Request;
import data.RequestDetail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
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
public class RequestController implements Initializable {

    @FXML
    private TableView<Request> RequestTable;
    
    
    @FXML
    private TableColumn<Request, String> requestId;
    @FXML
    private TableColumn<RequestDetail, String> productId;
    @FXML
    private TableColumn<RequestDetail, Integer> quantity;
    @FXML
    private TableColumn<Request, String> userId;
    @FXML
    private TableColumn<Request, String> startDate;
    @FXML
    private TableColumn<Request, String> statusVerify;

    
    private ObservableList<Request> requestList;
    private ObservableList<RequestDetail> requestDetailList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        requestList =FXCollections.observableArrayList(
        //new Request(requestId, userId, startDate, statusVerify)
        new Request("1", "ID001", "11/11/2020", "Yes"),
        new Request("2", "ID002", "12/12/2020", "No")       
        );
        requestDetailList = FXCollections.observableArrayList(
        //new RequestDetail(productId, requestId, 0)
        new RequestDetail("PR001", "1", 1),
        new RequestDetail("PR002", "2", 2)
        );
        requestId.setCellValueFactory(new PropertyValueFactory<Request, String>("requestId"));
        userId.setCellValueFactory(new PropertyValueFactory<Request, String>("userId"));
        startDate.setCellValueFactory(new PropertyValueFactory<Request, String>("startDate"));
        statusVerify.setCellValueFactory(new PropertyValueFactory<Request, String>("statusVerify"));
        productId.setCellValueFactory(new PropertyValueFactory<RequestDetail, String>("productId"));
        quantity.setCellValueFactory(new PropertyValueFactory<RequestDetail, Integer>("quantity"));
        RequestTable.setItems(requestList);
        //RequestTable.setItems(requestDetailList);
                
        
        
        
    }    
    
    
}
