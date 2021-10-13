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
public class EventDetail {
    private String eventId;
    private String productId;
    private String discount;
    private String mfdDate;
    private String expDate;
    
     public EventDetail() {
    }


    public EventDetail(String eventId, String productId, String discount, String mfdDate, String expDate) {
        this.eventId = eventId;
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
