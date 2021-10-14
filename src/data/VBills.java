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
public class VBills {
    private String billId;
    private String userId;
    private String transactionDate;
    private String statusCancel;
    private String paymentName;
    private String productId;
    private int quantity;
    private String mfgDate;
    private String expDate;

    public VBills() {
    }

    public VBills(String billId, String userId, String transactionDate, String statusCancel, String paymentName, String productId, int quantity, String mfgDate, String expDate) {
        this.billId = billId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.statusCancel = statusCancel;
        this.paymentName = paymentName;
        this.productId = productId;
        this.quantity = quantity;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatusCancel() {
        return statusCancel;
    }

    public void setStatusCancel(String statusCancel) {
        this.statusCancel = statusCancel;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
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
