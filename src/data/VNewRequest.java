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
public class VNewRequest {
    private String newRequestId;
    private String userId;
    private String startDate;
    private String statuVerify;
    private String newProductName;
    private int quantity;

    public VNewRequest() {
    }

    public VNewRequest(String newRequestId, String userId, String startDate, String statuVerify, String newProductName, int quantity) {
        this.newRequestId = newRequestId;
        this.userId = userId;
        this.startDate = startDate;
        this.statuVerify = statuVerify;
        this.newProductName = newProductName;
        this.quantity = quantity;
    }

    public String getNewRequestId() {
        return newRequestId;
    }

    public void setNewRequestId(String newRequestId) {
        this.newRequestId = newRequestId;
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

    public String getStatuVerify() {
        return statuVerify;
    }

    public void setStatuVerify(String statuVerify) {
        this.statuVerify = statuVerify;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
