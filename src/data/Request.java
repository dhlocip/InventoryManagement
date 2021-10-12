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
public class Request {
    private String requestId;
    private String userId;        
    private String startDate;
    private String statusVerify;

    public Request(String requestId, String userId, String startDate, String statusVerify) {
        this.requestId = requestId;
        this.userId = userId;
        this.startDate = startDate;
        this.statusVerify = statusVerify;
    }

    public Request() {
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

    
}
