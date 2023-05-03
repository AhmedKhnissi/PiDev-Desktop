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
    private float poids;
    private String nom;
    private User user;
    private int id_user;
        private int    categories_animal_id,animals_id;

    public Animal() {
    }

    public Animal(int age, String nom, float poids, int categories_animal_id, int animals_id) {
        this.age = age;
        this.nom = nom;
        this.poids = poids;
        this.categories_animal_id = categories_animal_id;
        this.animals_id = animals_id;
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

    public Animal(int id, int age, String nom, float poids, int categories_animal_id, int animals_id) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.poids = poids;
        this.categories_animal_id = categories_animal_id;
        this.animals_id = animals_id;
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

    public int getCategories_animal_id() {
        return categories_animal_id;
    }

    public void setCategories_animal_id(int categories_animal_id) {
        this.categories_animal_id = categories_animal_id;
    }

    public int getAnimals_id() {
        return animals_id;
    }

    public void setAnimals_id(int animals_id) {
        this.animals_id = animals_id;
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
        return "Animal{" + "id=" + id + ", age=" + age + ", poids=" + poids + ", nom=" + nom + ", user=" + user + ", id_user=" + id_user + ", categories_animal_id=" + categories_animal_id + ", animals_id=" + animals_id + '}';
    }

    
        
}
