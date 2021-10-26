/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class VBills {

    private String billId;
    private String userId;
    private Date transactionDate;
    private String statusCancel;
    private String paymentName;
    private String productId;
    private int quantity;
    private String mfgDate;
    private String expDate;
    private float price;
    private float total = quantity * price;

    public VBills() {
    }

    public VBills(String billId, String userId, Date transactionDate, String statusCancel, String paymentName, String productId, int quantity, String mfgDate, String expDate) {
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

    public VBills(String billId, String userId, Date transactionDate, String statusCancel, String paymentName, String productId, int quantity, String mfgDate, String expDate, float price, float total) {
        this.billId = billId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.statusCancel = statusCancel;
        this.paymentName = paymentName;
        this.productId = productId;
        this.quantity = quantity;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
        this.price = price;
        this.total = total;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
