/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Khalil
 */
public class Commentaire { 
    private int id; 
    private Publication publication ; 
    private user user ;  
    private String conten; 
    private Date datetime;  

    public Commentaire() {
    }

    public Commentaire(int id, Publication publication, user user, String conten, Date datetime) {
        this.id = id;
        this.publication = publication;
        this.user = user;
        this.conten = conten;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", publication=" + publication + ", user=" + user + ", conten=" + conten + ", datetime=" + datetime + '}';
    }
    
    
    
    
     
} 



