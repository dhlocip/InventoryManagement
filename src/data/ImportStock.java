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
public class ImportStock {
    private String importStockId;
    private String userId;
    private String supplierId;
    private String importDate;
    
    public ImportStock(String importStockId, String userId, String supplierId, String importDate) {
        this.importStockId = importStockId;
        this.userId = userId;
        this.supplierId = supplierId;
        this.importDate = importDate;
    }

    public ImportStock() {
    }

    public String getImportStockId() {
        return importStockId;
    }

    public void setImportStockId(String importStockId) {
        this.importStockId = importStockId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    
}
