/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khalil
 */
public class Publication { 
    private int id; 
    private String auteur; 
    private String titre; 
    private String contenu; 
    private String image; 
    private Date datepub; 
    private int likes=0; 
    private int dislike=0;   
    private int nbsignal=0;   
    private user user ;  
    private List<Commentaire> commentaires;
    
    public Publication() {
        commentaires = new ArrayList<>();
    }

    

    public Publication(int id, String auteur, String titre, String contenu, String image, Date datepub) {
        this.id = id;
        this.auteur = auteur;
        this.titre = titre;
        this.contenu = contenu;
        this.image = image;
        this.datepub = datepub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatepub() {
        return datepub;
    }

    public void setDatepub(Date datepub) {
        this.datepub = datepub;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(int nbsignal) {
        this.nbsignal = nbsignal;
    } 

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }
    public void addCommentaire(Commentaire commentaire) {
        commentaires.add(commentaire);
    }
    public List<Commentaire> getCommentaires() {
    return commentaires;
}


    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", auteur=" + auteur + ", titre=" + titre + ", contenu=" + contenu + ", image=" + image + ", datepub=" + datepub + ", likes=" + likes + ", dislike=" + dislike + ", nbsignal=" + nbsignal + '}';
    }

    public Publication(String auteur, String titre, String contenu, String image) { 
        this.auteur = auteur;
        this.titre = titre;
        this.contenu = contenu; 
        this.image = image; 
    }
    
    
    

    
    
    
    
    
    
}
