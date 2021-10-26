/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.Category;
import data_modifier.CategoryModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewCategoryController implements Initializable {

    @FXML
    private TextField searchTF;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categoryIdCol;
    @FXML
    private TableColumn<Category, String> categoryNameCol;
    @FXML
    private TableColumn<Category, String> descriptionCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getListCategory();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void getListCategory() throws SQLException {
        ObservableList<Category> oList = new CategoryModifier().getListCategory();

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryTableView.setItems(oList);
    }

    private void getListCategoryAfterSearch(String idOrName) throws SQLException {
        ObservableList<Category> oList = new CategoryModifier().getListCategoryAfterSearch(idOrName);

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryTableView.setItems(oList);
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getListCategoryAfterSearch(searchTF.getText());
    }

}
