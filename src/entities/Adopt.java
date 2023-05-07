/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author Soulaima_matmati
 */
public class Adopt {
    private int id,age;
    private String nom,gender,image,informations;
    private boolean sterelisation,vaccination;
    
     public Adopt() {
    }

    public Adopt(int age, String nom, String gender, String informations, String image, boolean sterelisation, boolean vaccination) {
        this.age = age;
        this.nom = nom;
        this.gender = gender;
        this.informations = informations;
        this.sterelisation = sterelisation;
        this.vaccination = vaccination;
        this.image = image;
    }

    public Adopt(int id, int age, String nom, String gender, String informations,String image, boolean sterelisation, boolean vaccination) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.gender = gender;
        this.informations = informations;
        this.sterelisation = sterelisation;
        this.vaccination = vaccination;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public boolean isSterelisation() {
        return sterelisation;
    }

    public void setSterelisation(boolean sterelisation) {
        this.sterelisation = sterelisation;
    }

    public boolean isVaccination() {
        return vaccination;
    }

    public void setVaccination(boolean vaccination) {
        this.vaccination = vaccination;
    }

    @Override
    public String toString() {
        return "Adopt{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", gender=" + gender + ", image=" + image + ", informations=" + informations + ", sterelisation=" + sterelisation + ", vaccination=" + vaccination + '}';
    }

    

   
     
     
}

    