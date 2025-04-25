/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server_project;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author nicole
 */
public class Pengguna implements Serializable{

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the tanggalLahir
     */
    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    /**
     * @param tanggalLahir the tanggalLahir to set
     */
    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    /**
     * @return the memberSince
     */
    public LocalDate getMemberSince() {
        return memberSince;
    }

    /**
     * @param memberSince the memberSince to set
     */
    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     // Constructor lengkap
    public Pengguna(int id, String nama, String username, String password, String email, String noTelp, String role, LocalDate tanggalLahir, LocalDate memberSince) {
        this.id = id;
        this.name = nama;
        this.username = username;
        this.password = password;
        this.email = email;
        this.no_hp = noTelp;
        this.role = role;
        this.tanggalLahir = tanggalLahir;
        this.memberSince = memberSince;
        
    }
    
    
   
    
    private int id;
    private String name;
    private String username;
    private String no_hp;
    private String password;
    private String email;
    private String role;
    private LocalDate tanggalLahir;
    private LocalDate memberSince;
    
    
}
