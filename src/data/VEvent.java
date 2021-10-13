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
public class VEvent {
    private String eventId;
    private String userId;
    private String eventName;
    private String startDate;
    private String endDate;
    private String productId;
    private String discount;
    private String mfdDate;
    private String expDate;
    
    public VEvent() {
    }

     public VEvent(String eventId, String userId, String eventName, String startDate, String endDate, String productId, String discount, String mfdDate, String expDate) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productId = productId;
        this.discount = discount;
        this.mfdDate = mfdDate;
        this.expDate = expDate;
    }
    
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMfdDate() {
        return mfdDate;
    }

    public void setMfdDate(String mfdDate) {
        this.mfdDate = mfdDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

   
    
}
