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
    private String companyName;
    private String homePage;

    public Suppliers(String personId, String fullName, String address, String gender, String phone) {
        super(personId, fullName, address, gender, phone);
    }

    public Suppliers(String companyName, String homePage, String personId, String fullName, String address, String gender, String phone) {
        super(personId, fullName, address, gender, phone);
        this.companyName = companyName;
        this.homePage = homePage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

   
   }
