/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
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
    private User user ;  

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    private List<Commentaire> commentaires; 
    private boolean likePressed = false;
    private boolean dislikePressed = false; 
    private boolean LikePressed = false;
    private boolean DislikePressed = false;  
    private int id_user;
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
    
    
    
    public boolean isLikePressed() {
        return likePressed;
    }

    public void setLikePressed(boolean likePressed) {
        this.likePressed = likePressed;
    }

    public boolean isDislikePressed() {
        return dislikePressed;
    }

    public void setDislikePressed(boolean dislikePressed) {
        this.dislikePressed = dislikePressed;
    }  
    public boolean isLikePressed2() {
        return LikePressed;
    }

    public void setLikePressed2(boolean likePressed) {
        this.LikePressed = likePressed;
    }

    public boolean isDislikePressed2() {
        return DislikePressed;
    }

    public void setDislikePressed2(boolean dislikePressed) {
        this.DislikePressed = dislikePressed;
    } 
    
    public void shareOnFacebook(Publication publication) {
    try {
        // Encode the title and content for use in the URL
        String encodedTitle = URLEncoder.encode(publication.getTitre(), "UTF-8");
        String encodedContent = URLEncoder.encode(publication.getContenu(), "UTF-8");
        
        // Construct the Facebook share URL
        String url = "https://www.facebook.com/sharer/sharer.php?u=" + encodedContent + "&quote=" + encodedTitle;
        
        // Open the URL in the user's default web browser
        Desktop.getDesktop().browse(new URI(url));
    } catch (Exception ex) {
        System.out.println("Error sharing on Facebook: " + ex.getMessage());
    }
}

   
    
    
}
    
    
    

    
    
    
    
    
    

