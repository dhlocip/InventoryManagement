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
public class NewRequest {
    private String newRequestId;
    private String userId;
    private String startDate;
    private String statuVerify;

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

    public NewRequest(String newRequestId, String userId, String startDate, String statuVerify) {
        this.newRequestId = newRequestId;
        this.userId = userId;
        this.startDate = startDate;
        this.statuVerify = statuVerify;
    }

    public NewRequest() {
    }
}   
