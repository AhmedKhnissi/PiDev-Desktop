/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author heha
 */
public class user {
     private int id, telephone;
    private String nom, prenom, email, pays, gouvernorat, ville, rue,roles;

    public user() {
    }

    public user(int telephone, String nom, String prenom, String email, String pays, String gouvernorat, String ville, String rue, String roles) {
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.roles = roles;
    }

    public user(int id, int telephone, String nom, String prenom, String email, String pays, String gouvernorat, String ville, String rue, String roles) {
        this.id = id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEMail() {
        return email;
    }

    public void setEMail(String email) {
        this.email = email;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", telephone=" + telephone + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pays=" + pays + ", gouvernorat=" + gouvernorat + ", ville=" + ville + ", rue=" + rue + ", roles=" + roles + '}';
    }
    
    
}
