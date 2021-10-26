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
    private String eventIdDetail;
    private String productId;
    private String discount;
    private String mfgDate;
    private String expDate;

    public EventDetail() {
    }

    public EventDetail(String eventIdDetail, String productId, String discount, String mfgDate, String expDate) {
        this.eventIdDetail = eventIdDetail;
        this.productId = productId;
        this.discount = discount;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
    }

    public String getEventIdDetail() {
        return eventIdDetail;
    }

    public void setEventIdDetail(String eventIdDetail) {
        this.eventIdDetail = eventIdDetail;
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
