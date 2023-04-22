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
    private int id,age;
    private String nom;
    private float poids;
       

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

    
 

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", poids=" + poids + '}';
    }
        
}
