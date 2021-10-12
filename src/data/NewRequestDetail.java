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
public class NewRequestDetail {
    private String newRequestId;
    private String newProductName;
    private int quantity;

    public String getNewRequestId() {
        return newRequestId;
    }

    public void setNewRequestId(String newRequestId) {
        this.newRequestId = newRequestId;
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

    public NewRequestDetail(String newRequestId, String newProductName, int quantity) {
        this.newRequestId = newRequestId;
        this.newProductName = newProductName;
        this.quantity = quantity;
    }

    public NewRequestDetail() {
    }
}
