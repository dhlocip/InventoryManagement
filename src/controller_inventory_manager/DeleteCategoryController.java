/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.BillDetail;
import data.Category;
import data.ImportStockDetail;
import data_modifier.BillDetailModifier;
import data_modifier.CategoryModifier;
import data_modifier.EventDetailModifier;
import data_modifier.ImportStockDetailModifier;
import data_modifier.ImportStockModifier;
import data_modifier.ProductModifier;
import data_modifier.RequestDetailModifier;
import data_modifier.SupplierModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class DeleteCategoryController implements Initializable {

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
            Logger.getLogger(DeleteCategoryController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void categoryTableViewClicked(MouseEvent event) throws SQLException {
        Category item = categoryTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on a non-empty line.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the line.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                List<String> listProductId = new ProductModifier().getListProductId(item.getCategoryId());
                for (String productId : listProductId) {
                    new ImportStockDetailModifier().deleteImportStockDetail(productId);
                    new BillDetailModifier().deleteBillDetail(productId);
                    new RequestDetailModifier().deleteRequestDetail(productId);
                    new EventDetailModifier().deleteEventDetail(productId);
                    new ProductModifier().deleteProduct(productId);
                }
                new CategoryModifier().deleteCategory(item.getCategoryId());
                getListCategory();
            }
        }
    }

}
