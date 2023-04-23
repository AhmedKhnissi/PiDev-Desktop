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
    private int pub_id ; 
    private user user ;  
    private String contenu; 
    private Date datetime;  

    public Commentaire() {
    }

    public Commentaire(int id, Publication publication, user user, String contenu, Date datetime) {
        this.id = id;
        this.publication = publication;
        this.user = user;
        this.contenu = contenu;
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
    public int getPublicationID() {
        return publication.getId();
    } 

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
 public void setPublicationId(int pub_id) {
    this.pub_id = pub_id;
}

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String conten) {
        this.contenu = conten;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", publication=" + publication + ", user=" + user + ", conten=" + contenu + ", datetime=" + datetime + '}';
    }

    public Commentaire(int pub_id, String contenu, Date datetime) {
        this.pub_id = pub_id;
        this.contenu = contenu;
        this.datetime = datetime;
    }
    public Commentaire(Publication publication, String contenu, Date datetime) {
        this.publication = publication;
        this.contenu = contenu;
        this.datetime = datetime;
    }
    
    
    
     
} 



