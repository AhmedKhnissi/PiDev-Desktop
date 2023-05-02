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
public class Animal {
    private int id;
    private int age;
    private String nom;
    private float poids;
    private User user;
    private int id_user;

       

    public Animal() {
    }

    public Animal(int age, String nom, float poids) {
        this.age = age;
        this.nom = nom;
        this.poids = poids;
   
    }

   

    public Animal(String nom, int age, float poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
        
        
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

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    
    

    
 

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", poids=" + poids + '}';
    }
        
}
