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
public class Suppliers extends Person{
    private String supplierId;
    private String companyName;
    private String address;
    private String phone;
    private String homePage;
    private String personRepresentative;

    public Suppliers() {
    }

    public Suppliers(String companyName, String address, String phone, String homePage, String personRepresentative) {
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
        this.homePage = homePage;
        this.personRepresentative = personRepresentative;
    }
    
    public Suppliers(String supplierId, String companyName, String address, String phone, String homePage, String personRepresentative) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
        this.homePage = homePage;
        this.personRepresentative = personRepresentative;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getPersonRepresentative() {
        return personRepresentative;
    }

    public void setPersonRepresentative(String personRepresentative) {
        this.personRepresentative = personRepresentative;
    }
    
   }
