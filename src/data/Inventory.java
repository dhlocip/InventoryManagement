/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author sa
 */
public class Inventory {
    private String categoryId;
    private String productId;
    private String productName;
    private int quantity;
    private String mfgDate;
    private String expDate;

    public Inventory() {
    }

    public Inventory(String categoryId, String productId, String productName, int quantity, String mfgDate, String expDate) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(String mfgDate) {
        this.mfgDate = mfgDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    
    
}
