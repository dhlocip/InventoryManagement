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
public class BillStatistic {
    private String productId;
    private String billId;
    private int quantity;
    private float price;
    private String mfgDate;
    private String expDate;
    private String userId;
    private String transactionDate;
    private String statusCancel;
    private String paymentName;
    private float total;
    private float revenue;
    private float totalCancel;

    public BillStatistic() {
    }

    public BillStatistic(String productId, String billId, int quantity, float price, String mfgDate, String expDate, String userId, String transactionDate, String statusCancel, String paymentName, float total, float revenue, float totalCancel) {
        this.productId = productId;
        this.billId = billId;
        this.quantity = quantity;
        this.price = price;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.statusCancel = statusCancel;
        this.paymentName = paymentName;
        this.total = total;
        this.revenue = revenue;
        this.totalCancel = totalCancel;
    }

   

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public float getTotalCancel() {
        return totalCancel;
    }

    public void setTotalCancel(float totalCancel) {
        this.totalCancel = totalCancel;
    }

    
   

}
