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
public class VRequest {
    
    private String requestId;
    private String userId;        
    private String startDate;
    private String statusVerify;
    private String productId;
    private int quantity;

    public VRequest() {
    }

    public VRequest(String requestId, String userId, String startDate, String statusVerify, String productId, int quantity) {
        this.requestId = requestId;
        this.userId = userId;
        this.startDate = startDate;
        this.statusVerify = statusVerify;
        this.productId = productId;
        this.quantity = quantity;
    }

    public VRequest(String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatusVerify() {
        return statusVerify;
    }

    public void setStatusVerify(String statusVerify) {
        this.statusVerify = statusVerify;
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
    
    
    
}
