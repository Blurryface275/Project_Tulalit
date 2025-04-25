/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server_project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author hanse
 */
public class Tiket {

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public LocalTime getJamEvent() {
        return jamEvent;
    }

    public void setJamEvent(LocalTime jamEvent) {
        this.jamEvent = jamEvent;
    }

    public LocalDateTime getTiketCloseDate() {
        return tiketCloseDate;
    }

    public void setTiketCloseDate(LocalDateTime tiketCloseDate) {
        this.tiketCloseDate = tiketCloseDate;
    }

    public int getRegTickets() {
        return regTickets;
    }

    public void setRegTickets(int regTickets) {
        this.regTickets = regTickets;
    }

    public int getVipTickets() {
        return vipTickets;
    }

    public void setVipTickets(int vipTickets) {
        this.vipTickets = vipTickets;
    }

    public double getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(double regPrice) {
        this.regPrice = regPrice;
    }

    public double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Tiket() {

    }

    public Tiket(String creatorName, String eventName, String location, LocalDate eventDate, double regPrice, double vipPrice, int stock, LocalDateTime endTime) { // constructor ini buat di client sebenarnya, biar sama saja 
        this.creatorName = creatorName;
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.regPrice = regPrice;
        this.vipPrice = vipPrice;
        this.stock = stock;
        this.tiketCloseDate = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus() {
        if (getTiketCloseDate().isAfter(tiketCloseDate)) {
            this.status = 0; //open
        }
        this.status = 1;//close
    }

    public Tiket(String creatorName, String eventName, String location, LocalDate eventDate, LocalTime jam, int reg, int vip, double regPrice, double vipPrice, LocalDateTime endTime, String kategori) { //this constructor untuk pas buat tiket
        this.creatorName = creatorName; // pembuat event
        this.eventName = eventName; // nama event
        this.location = location; // lokasi event
        this.eventDate = eventDate; // tanggal event
        this.jamEvent = jam; // jam event
        this.regTickets = reg; // stock tiket reguler
        this.vipTickets = vip; // stock tiket vip
        this.regPrice = regPrice; // harga tiket reguler
        this.vipPrice = vipPrice; // harga tiket vip
        this.tiketCloseDate = endTime; // tanggal kapan terakhir pembelian tiket tutup
        this.stock = reg + vip;
        this.kategori = kategori;
    }

    private String creatorName;
    private String eventName;
    private String location;
    private LocalDate eventDate;
    private int stock;
    private LocalTime jamEvent;
    private LocalDateTime tiketCloseDate;
    private int regTickets;
    private int vipTickets;
    private double regPrice;
    private double vipPrice;
    private String kategori;
    private int status;

}
