/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author sa
 */
public class VImportStock {

    private String importStockId;
    private String supplierId;
    private String importDate;
    private String productId;
    private int quantity;
    private double price;
    private String mfgDate;
    private String expDate;

    public VImportStock() {
    }

    public VImportStock(String importStockId, String supplierId, String importDate, String productId, int quantity, double price, String mfgDate, String expDate) {
        this.importStockId = importStockId;
        this.supplierId = supplierId;
        this.importDate = importDate;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
    }

    public String getImportStockId() {
        return importStockId;
    }

    public void setImportStockId(String importStockId) {
        this.importStockId = importStockId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
