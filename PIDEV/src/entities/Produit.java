/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author admin
 */
public class Produit {
    private int id, stock;
    private String nom, description;
    private float prix ;
    private String image ;

    public Produit(String nom, String description, int stock, float prix, String image) {
        this.nom = nom;
        this.description = description;
        this.stock = stock;
        this.prix = prix;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Produit() {
    }

    public Produit(int id, String nom, String description,  int stock, float prix) {
        this.id = id;
         this.nom = nom;
         this.description = description;
        this.stock = stock;
        this.prix = prix;
    }

    public Produit( String nom, String description,int stock, float prix) {
        this.nom = nom;
        this.description = description;
        this.stock = stock;
        this.prix = prix;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "stock=" + stock + ", nom=" + nom + ", description=" + description + ", prix=" + prix + '}';
    }

}
