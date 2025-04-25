/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiproject;

import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class Keranjang {
    
    public Keranjang(String usernameBuyer, String creatorName, String eventName, String jenis, double price, int quantity, double total) {
        this.usernameBuyer = usernameBuyer;
        this.creatorName = creatorName;
        this.eventName = eventName;
        this.jenis = jenis;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUsernameBuyer() {
        return usernameBuyer;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    private String usernameBuyer;
    private String creatorName;
    private String eventName;
    private String jenis;
    private double price;
    private int quantity;
    private double total;
}
