/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author ADMIN
 */
public class ImportStockDetail {
    private String importStockId;
    private String productId;
    private int quantity;
    private double price;
    private String mfgDate;
    private String expDate;

    public String getImportStockId() {
        return importStockId;
    }

    public void setImportStockId(String importStockId) {
        this.importStockId = importStockId;
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

    public ImportStockDetail(String importStockId, String productId, int quantity, double price, String mfgDate, String expDate) {
        this.importStockId = importStockId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
    }

    public ImportStockDetail() {
    }
    
}
