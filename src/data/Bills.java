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
public class Bills {
    private String billId;
    private String userId;
    private String transactionDate;
    private String statusCancel;
    private String paymentName;
    
    public Bills(String billId, String userId, String transactionDate, String statusCancel, String paymentName) {
        this.billId = billId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.statusCancel = statusCancel;
        this.paymentName = paymentName;
    }

    public Bills() {
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

    
}
